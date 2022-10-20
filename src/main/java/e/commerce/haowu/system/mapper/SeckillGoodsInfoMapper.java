package e.commerce.haowu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import e.commerce.haowu.system.entity.SeckillGoodsInfo;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.vo.SeckillGoodsInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bobo
 * @since 2022-02-20
 */
@Mapper
public interface SeckillGoodsInfoMapper extends BaseMapper<SeckillGoodsInfo> {

    List<SeckillGoodsInfoVo> listSeckillGoods();

    SeckillGoodsInfoVo findGoodsVoByGoodsId(@Param("goodsId") Long goodsId);
}
