package e.commerce.haowu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.dto.RespBeanEnum;
import e.commerce.haowu.frame.utils.MD5Util;
import e.commerce.haowu.frame.utils.UUIDUtil;
import e.commerce.haowu.system.entity.DeliveryAddrInfo;
import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.entity.OrderInfo;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.mapper.GoodsInfoMapper;
import e.commerce.haowu.system.mapper.OrderInfoMapper;
import e.commerce.haowu.system.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import e.commerce.haowu.system.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    GoodsInfoMapper goodsInfoMapper;
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    RedisTemplate redisTemplate;

    //保存订单
    @Override
    public RespBean saveEntity(OrderInfo entity) {
        //检验登录
        if (entity.getUserId() == null){
            return RespBean.error(RespBeanEnum.USER_NOT_LOGIN);
        }
        //检验库存
        GoodsInfo goodsInfo = goodsInfoMapper.selectById(entity.getGoodsId());
        if (goodsInfo.getGoodsStock() < entity.getGoodsCount()){
            return RespBean.error(RespBeanEnum.STOCK_INSUFFICIENT);
        }
        //检查购买的是否一致

        entity.setCreateTime(new Date());
        int insert = baseMapper.insert(entity);
        if (insert <= 0){
            return RespBean.error(RespBeanEnum.BUY_ERROR);
        }
        return RespBean.success("添加购物车成功！");
    }

    //根据用户查询订单表
    @Override
    public List<OrderInfo> selectByUserId(Long userId) {
        return orderInfoMapper.selectByUserId(userId);
    }

    //更新地址信息
    @Override
    public RespBean updateDeliveryAddrByUserId(Long userId, Long deliveryAddrId) {
        int i = baseMapper.updateDeliveryAddrByUserId(userId, deliveryAddrId);

        if (i <= 0){
            return RespBean.error(RespBeanEnum.PAY_ERROR);
        }
        return RespBean.success("支付成功");
    }

    //后台展示订单表
    @Override
    public RespBean listByTable(OrderVo entity) {
        return RespBean.table(baseMapper.countByTable(entity),baseMapper.listByTable(entity));
    }

    //后台展示历史订单表
    @Override
    public RespBean listHistory(OrderVo entity) {
        return RespBean.table(baseMapper.countHistory(entity),baseMapper.listHistory(entity));
    }

    //发货
    @Override
    public RespBean updateStatusById(int id) {
        int i = baseMapper.updateStatusById(id);
        if (i <= 0){
            return RespBean.error(RespBeanEnum.ORDER_CHECK_OUT_ERROR);
        }
        return RespBean.success("发货成功");
    }

    //校验验证码
    @Override
    public boolean checkCaptcha(UserInfo user, Long goodsId, String captcha) {
        if (user == null || StringUtils.isBlank(captcha) || goodsId < 0) {
            return false;
        }
        String redisCaptcha = (String) redisTemplate.opsForValue().get("captcha:" + user.getId() + ":" + goodsId);
        return redisCaptcha.equals(captcha);
    }

    //校验秒杀地址
    @Override
    public boolean checkPath(UserInfo user, Long goodsId, String path) {
        if (user == null || goodsId < 0 || StringUtils.isBlank(path)) {
            return false;
        }
        String redisPath = (String) redisTemplate.opsForValue().get("seckillPath:" + user.getId() + ":" + goodsId);
        return path.equals(redisPath);
    }

    //隐藏秒杀接口
    @Override
    public String createPath(UserInfo user, Long goodsId) {
        //随机生成秒杀接口的地址
        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        //将随机生成的接口地址放入redis缓存中保存60秒的时间
        redisTemplate.opsForValue().set("seckillPath:" + user.getId() + ":" + goodsId, str, 60, TimeUnit.SECONDS);
        return str;
    }

}
