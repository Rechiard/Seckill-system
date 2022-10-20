package e.commerce.haowu.system.service;

import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.system.entity.DeliveryAddrInfo;
import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.vo.OrderVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
public interface OrderInfoService extends IService<OrderInfo> {


    RespBean saveEntity(OrderInfo entity);

    List<OrderInfo> selectByUserId(Long userId);

    RespBean updateDeliveryAddrByUserId(Long userId,Long deliveryAddrId);

    RespBean listByTable(OrderVo entity);

    RespBean listHistory(OrderVo entity);

    RespBean updateStatusById(int id);

    boolean checkCaptcha(UserInfo user, Long goodsId, String captcha);

    String createPath(UserInfo user, Long goodsId);

    boolean checkPath(UserInfo user, Long goodsId, String path);


}
