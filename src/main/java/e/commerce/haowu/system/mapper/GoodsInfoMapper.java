package e.commerce.haowu.system.mapper;

import e.commerce.haowu.system.entity.GoodsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import e.commerce.haowu.system.entity.UserInfo;
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
public interface GoodsInfoMapper extends BaseMapper<GoodsInfo> {

    List<GoodsInfo> listByType(@Param("entity")GoodsInfo entity);

    int countByTable(@Param("entity") GoodsInfo entity);

    List<GoodsInfo> listByTable(@Param("entity") GoodsInfo entity);

}
