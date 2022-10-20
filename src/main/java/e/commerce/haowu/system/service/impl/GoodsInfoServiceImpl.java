package e.commerce.haowu.system.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.dto.RespBeanEnum;
import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.mapper.GoodsInfoMapper;
import e.commerce.haowu.system.service.GoodsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Service
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements GoodsInfoService {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public List<GoodsInfo> listByType(GoodsInfo goodsInfo) {
        return goodsInfoMapper.listByType(goodsInfo);
    }

    @Override
    public GoodsInfo selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public RespBean listByTable(GoodsInfo entity) {
        return RespBean.table(baseMapper.countByTable(entity),baseMapper.listByTable(entity));
    }

    @Override
    public RespBean insert(GoodsInfo entity) {
        int insert = goodsInfoMapper.insert(entity);
        if (insert <= 0){
            return RespBean.error(RespBeanEnum.GOODS_INSERT_ERROR);
        }
        return RespBean.success("新增成功");
    }

}
