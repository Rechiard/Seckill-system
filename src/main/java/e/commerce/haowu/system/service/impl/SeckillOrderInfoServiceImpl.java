package e.commerce.haowu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import e.commerce.haowu.system.entity.SeckillOrderInfo;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.mapper.SeckillOrderInfoMapper;
import e.commerce.haowu.system.service.SeckillOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobo
 * @since 2022-02-20
 */
@Service
public class SeckillOrderInfoServiceImpl extends ServiceImpl<SeckillOrderInfoMapper, SeckillOrderInfo> implements SeckillOrderInfoService {

    @Autowired
    private SeckillOrderInfoMapper seckillOrderInfoMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Long getResult(UserInfo user, Long goodsId) {
        SeckillOrderInfo seckillOrder = seckillOrderInfoMapper.selectOne(new QueryWrapper<SeckillOrderInfo>().eq("user_id", user.getId()).eq("goods_id", goodsId));
        if (null != seckillOrder) {
            return seckillOrder.getOrderId();
        } else if (redisTemplate.hasKey("isStockEmpty:" + goodsId)) {
            return -1L;
        } else {
            return 0L;
        }
    }
}
