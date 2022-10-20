package e.commerce.haowu.frame.rabbitmq;

import e.commerce.haowu.frame.utils.JsonUtil;
import e.commerce.haowu.system.entity.*;
import e.commerce.haowu.system.mapper.SeckillGoodsInfoMapper;
import e.commerce.haowu.system.service.GoodsInfoService;
import e.commerce.haowu.system.service.SeckillGoodsInfoService;
import e.commerce.haowu.system.vo.SeckillGoodsInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQReceiver {

    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SeckillGoodsInfoMapper seckillGoodsInfoMapper;
    @Autowired
    SeckillGoodsInfoService seckillGoodsInfoService;

    //原始 QPS 785.9/sec
    //页面静态化和限流 QPS 1351.2/sec
    //MQ异步和Redis缓存QPS 2519.3/sec

    //当消息队列接受到了redis传过来的数据之后，就该下单操作了
    @RabbitListener(queues = "seckillQueue")
    public void receive(String message) {
        log.info("接受到的消息：" + message);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(message, SeckillMessage.class);
        Long goodId = seckillMessage.getGoodId();
        UserInfo user = seckillMessage.getUser();

        //库存是否充足
        SeckillGoodsInfoVo seckillGoodsInfoVo = seckillGoodsInfoService.findGoodsVoByGoodsId(goodId);
        if (seckillGoodsInfoVo.getGoodsStock() < 1) {
            return;
        }

        //是否有人重复抢购
        SeckillOrderInfo seckillOrder = (SeckillOrderInfo) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodId);
        if (seckillOrder != null) {
            return;
        }
        //下单
        seckillGoodsInfoService.seckill(user, seckillGoodsInfoVo);

    }

}
