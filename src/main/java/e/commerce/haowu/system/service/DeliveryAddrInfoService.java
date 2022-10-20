package e.commerce.haowu.system.service;

import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.system.entity.DeliveryAddrInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
public interface DeliveryAddrInfoService extends IService<DeliveryAddrInfo> {

    RespBean listByTable(DeliveryAddrInfo entity);

    RespBean saveEntity(DeliveryAddrInfo entity);

    RespBean updateEntity(DeliveryAddrInfo entity);

}
