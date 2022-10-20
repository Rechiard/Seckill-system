package e.commerce.haowu.system.mapper;

import e.commerce.haowu.system.entity.UserInfo;
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
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    int insert(@Param("entity") UserInfo userInfo);

    int countByTable(@Param("entity") UserInfo entity);

    List<UserInfo> listByTable(@Param("entity") UserInfo entity);
}
