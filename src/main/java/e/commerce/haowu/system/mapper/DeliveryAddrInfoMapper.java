package e.commerce.haowu.system.mapper;

import e.commerce.haowu.system.entity.DeliveryAddrInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface DeliveryAddrInfoMapper extends BaseMapper<DeliveryAddrInfo> {

    int countByTable(@Param("entity") DeliveryAddrInfo entity);

    List<DeliveryAddrInfo> listByTable(@Param("entity") DeliveryAddrInfo entity);
}
