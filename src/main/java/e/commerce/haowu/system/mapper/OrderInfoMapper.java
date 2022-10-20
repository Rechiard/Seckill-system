package e.commerce.haowu.system.mapper;

import e.commerce.haowu.system.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    List<OrderInfo> selectByUserId(@Param("userId") Long userId);

    int updateDeliveryAddrByUserId(@Param("userId")Long userId,@Param("deliveryAddrId")Long deliveryAddrId);

    int countByTable(@Param("entity") OrderVo entity);

    List<OrderVo> listByTable(@Param("entity") OrderVo entity);

    int countHistory(@Param("entity") OrderVo entity);

    List<OrderVo> listHistory(@Param("entity") OrderVo entity);

    int updateStatusById(@Param("id")int id);


}
