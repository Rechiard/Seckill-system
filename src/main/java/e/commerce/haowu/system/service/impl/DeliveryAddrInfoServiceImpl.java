package e.commerce.haowu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.dto.RespBeanEnum;
import e.commerce.haowu.system.entity.DeliveryAddrInfo;
import e.commerce.haowu.system.mapper.DeliveryAddrInfoMapper;
import e.commerce.haowu.system.service.DeliveryAddrInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Service
public class DeliveryAddrInfoServiceImpl extends ServiceImpl<DeliveryAddrInfoMapper, DeliveryAddrInfo> implements DeliveryAddrInfoService {


    @Override
    public RespBean listByTable(DeliveryAddrInfo entity) {
        return RespBean.table(baseMapper.countByTable(entity),baseMapper.listByTable(entity));
    }

    @Override
    public RespBean saveEntity(DeliveryAddrInfo entity) {
        int insert = baseMapper.insert(entity);
        if (insert <= 0){
            return RespBean.error(RespBeanEnum.DELIVERY_INSERT_ERROR);
        }
        return RespBean.success("新增地址成功");
    }

    @Override
    public RespBean updateEntity(DeliveryAddrInfo entity) {
        int i = baseMapper.updateById(entity);
        if (i <= 0){
            return RespBean.error(RespBeanEnum.DELIVERY_UPDATE_ERROR);
        }
        return RespBean.success("更新地址成功");
    }
}
