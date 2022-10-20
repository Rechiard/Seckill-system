package e.commerce.haowu.system.service;

import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.system.entity.GoodsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import e.commerce.haowu.system.entity.UserInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
public interface GoodsInfoService extends IService<GoodsInfo> {

    List<GoodsInfo> listByType(GoodsInfo goodsInfo);

    GoodsInfo selectById(Long id);

    RespBean listByTable(GoodsInfo entity);

    RespBean insert(GoodsInfo entity);


}
