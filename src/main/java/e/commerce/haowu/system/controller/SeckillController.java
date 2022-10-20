package e.commerce.haowu.system.controller;


import com.wf.captcha.ArithmeticCaptcha;
import e.commerce.haowu.frame.config.AccessLimit;
import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.dto.RespBeanEnum;
import e.commerce.haowu.frame.exception.GlobalException;
import e.commerce.haowu.frame.rabbitmq.MQSender;
import e.commerce.haowu.frame.utils.JsonUtil;
import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.entity.SeckillMessage;
import e.commerce.haowu.system.entity.SeckillOrderInfo;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.service.GoodsInfoService;
import e.commerce.haowu.system.service.OrderInfoService;
import e.commerce.haowu.system.service.SeckillOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private SeckillOrderInfoService seckillOrderInfoService;
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private MQSender mqSender;
    @Autowired
    private RedisScript<Long> script;

    private Map<Long, Boolean> EmptyStockMap = new HashMap<>();

    //验证码接口
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void verifyCode(UserInfo user, Long goodsId, HttpServletResponse response) {
        if (goodsId < 0) {
            throw new GlobalException(RespBeanEnum.REQUEST_ILLEGAL);
        }
        //设置请求头为输出图片的类型
        response.setContentType("image/jpg");
        //设置不需要缓存，保证每次都是新的验证码
        response.setHeader("Program", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        //生成验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 32, 3);
        //将验证码放入redis中
        redisTemplate.opsForValue().set("captcha:" + user.getId() + ":" + goodsId, captcha.text(), 300, TimeUnit.SECONDS);
        try {
            captcha.out(response.getOutputStream());
            System.out.println();
        } catch (IOException e) {
            log.error("验证码生成失败", e.getMessage());
        }
    }

    //隐藏秒杀接口地址
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    //限流
    @AccessLimit(second = 5, maxCount = 5, needLogin = true)
    public RespBean getPath(UserInfo user, Long goodsId, String captcha, HttpServletRequest request) {
        if (user == null) {
            RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        //判断验证码是否正确
        boolean check = orderInfoService.checkCaptcha(user, goodsId, captcha);
        if (!check) {
            return RespBean.error(RespBeanEnum.ERROR_CAPTCHA);
        }

        //创造伪秒杀接口
        String str = orderInfoService.createPath(user, goodsId);
        return RespBean.success(str);
    }


    /*
    秒杀优化在于减少对数据库的访问：先用Redis对库存进行预处理，防止多次访问数据库
    再将Redis中的库存进行判断，如果已经达到库存最大值则直接访问数据库
    否则将redis中的数据信息压入rabbitmq的消息队列中慢慢处理，这样就可以达到提高性能的作用了
 */
    @RequestMapping(value = "/{path}/doSeckill", method = RequestMethod.GET)
    @ResponseBody
    public RespBean doSeckill(@PathVariable String path, UserInfo user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        ValueOperations valueOperations = redisTemplate.opsForValue();


        //判断加密过的接口地址是否正确
        boolean check = orderInfoService.checkPath(user, goodsId, path);
        if (!check) {
            return RespBean.error(RespBeanEnum.REQUEST_ILLEGAL);
        }


        //重复抢购判断模块
        SeckillOrderInfo seckillOrder = (SeckillOrderInfo) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (seckillOrder != null) {
            System.out.println("重复抢购");
            return RespBean.error(RespBeanEnum.REPEAT_ERROR);
        }


        //通过内存标记，减少Redis的访问
        if (EmptyStockMap.get(goodsId)) {
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //利用lua脚本实现分布式锁，保证数据的一致性和准确性。
        //lua脚本分为两种使用方式：1.java编写，每次运行调用给服务器(容易改变,但会降低性能)；2，服务器编写，java直接调用(性能好一点，但不容易改变)
        Long stock = (Long) redisTemplate.execute(script, Collections.singletonList("seckillGoods:" + goodsId), Collections.EMPTY_LIST);

        System.out.println("stock= " + stock);

        //如果库存不足，添加到Redis缓存中加快判断，避免访问后续接口
        if (stock < 0) {
            EmptyStockMap.put(goodsId, true);
            //这一步是让 redis中的库存 -1 变成 0 ，让数据库中的数据好看一点
            valueOperations.increment("seckillGoods:" + goodsId);
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }


        //利用mq做异步处理
        SeckillMessage seckillMessage = new SeckillMessage(user, goodsId);
        mqSender.sendSeckillMessage(JsonUtil.object2JsonStr(seckillMessage));

        return RespBean.success(0);
    }

    //秒杀之后判断是否秒杀成功结果的接口,goodsId成功，-1是失败，0是排队中
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public RespBean getResult(UserInfo user, Long goodsId) {
        if (user == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        Long orderId = seckillOrderInfoService.getResult(user, goodsId);
        return RespBean.success(orderId);
    }


    //系统初始化，用于将商品库存数量加载到Redis中
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsInfo> list = goodsInfoService.list();
        for (GoodsInfo goodsInfo : list) {
            System.out.println(goodsInfo);
        }
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        list.forEach(goodsVo -> {
            redisTemplate.opsForValue().set("seckillGoods:" + goodsVo.getId(), goodsVo.getGoodsStock());
            EmptyStockMap.put(goodsVo.getId(), false);
        });
    }

}
