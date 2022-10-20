package e.commerce.haowu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import e.commerce.haowu.system.entity.SeckillGoodsInfo;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.vo.SeckillGoodsInfoVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobo
 * @since 2022-02-20
 */
public interface SeckillGoodsInfoService extends IService<SeckillGoodsInfo> {

    List<SeckillGoodsInfoVo> listSeckillGoods();

    SeckillGoodsInfoVo findGoodsVoByGoodsId(Long goodsId);

    void seckill(UserInfo user, SeckillGoodsInfoVo seckillGoodsInfoVo);
}
