package e.commerce.haowu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import e.commerce.haowu.system.entity.*;
import e.commerce.haowu.system.mapper.OrderInfoMapper;
import e.commerce.haowu.system.mapper.SeckillGoodsInfoMapper;
import e.commerce.haowu.system.mapper.SeckillOrderInfoMapper;
import e.commerce.haowu.system.service.GoodsInfoService;
import e.commerce.haowu.system.service.SeckillGoodsInfoService;
import e.commerce.haowu.system.service.SeckillOrderInfoService;
import e.commerce.haowu.system.vo.SeckillGoodsInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobo
 * @since 2022-02-20
 */
@Service
public class SeckillGoodsInfoServiceImpl extends ServiceImpl<SeckillGoodsInfoMapper, SeckillGoodsInfo> implements SeckillGoodsInfoService {

    @Autowired
    SeckillGoodsInfoMapper seckillGoodsInfoMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    GoodsInfoService goodsInfoService;
    @Autowired
    SeckillOrderInfoMapper seckillOrderInfoMapper;
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Override
    public List<SeckillGoodsInfoVo> listSeckillGoods() {
        return seckillGoodsInfoMapper.listSeckillGoods();
    }

    @Override
    public SeckillGoodsInfoVo findGoodsVoByGoodsId(Long goodsId) {
        return seckillGoodsInfoMapper.findGoodsVoByGoodsId(goodsId);
    }

    /**
     * 秒杀功能
     * @param user
     * @param seckillGoodsInfoVo
     */
    @Override
    @Transactional
    public void seckill(UserInfo user, SeckillGoodsInfoVo seckillGoodsInfoVo) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //商品减库存
        GoodsInfo goodsInfo = goodsInfoService.getOne(new QueryWrapper<GoodsInfo>().eq("id", seckillGoodsInfoVo.getGoodsId()));
        goodsInfo.setGoodsStock((goodsInfo.getGoodsStock() - 1));
        System.out.println("我的库存为：" + goodsInfo.getGoodsStock());

        //防止超卖
        boolean result = goodsInfoService.update(new UpdateWrapper<GoodsInfo>()
                .setSql("goods_stock = goods_stock -1").eq("id", seckillGoodsInfoVo.getGoodsId()).gt("goods_stock", 0));

        //判断是否还有库存
        if (goodsInfo.getGoodsStock() < 1) {
            valueOperations.set("isStockEmpty:" + seckillGoodsInfoVo.getGoodsId(), "0");
            return ;
        }

        //生成订单
        OrderInfo order = new OrderInfo();
        order.setUserId(user.getId());
        order.setGoodsId(seckillGoodsInfoVo.getGoodsId());
        order.setGoodsName(seckillGoodsInfoVo.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoodsInfoVo.getSeckillPrice());
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setGoodsCapacity("32GB");
        order.setStatus(1);
        order.setGoodsImg(seckillGoodsInfoVo.getGoodsImg());
        orderInfoMapper.insert(order);

        //生成秒杀订单
        SeckillOrderInfo seckillOrder = new SeckillOrderInfo();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(seckillGoodsInfoVo.getGoodsId());
        seckillOrderInfoMapper.insert(seckillOrder);

        //将秒杀订单存在redis中，从而不用在数据库中获取订单，优化作用!
        redisTemplate.opsForValue().set("order:" + user.getId() + ":" + seckillGoodsInfoVo.getGoodsId(), seckillOrder);
    }
}
