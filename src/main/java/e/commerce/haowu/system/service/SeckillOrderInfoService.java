package e.commerce.haowu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import e.commerce.haowu.system.entity.SeckillOrderInfo;
import e.commerce.haowu.system.entity.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobo
 * @since 2022-02-20
 */
public interface SeckillOrderInfoService extends IService<SeckillOrderInfo> {

    Long getResult(UserInfo user, Long goodsId);
}
