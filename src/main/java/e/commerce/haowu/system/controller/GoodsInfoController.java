package e.commerce.haowu.system.controller;


import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.dto.RespBeanEnum;
import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.mapper.SeckillGoodsInfoMapper;
import e.commerce.haowu.system.service.GoodsInfoService;
import e.commerce.haowu.system.vo.DetailVo;
import e.commerce.haowu.system.vo.SeckillGoodsInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Controller
@RequestMapping("/system/goodsInfo")
public class GoodsInfoController {

    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private SeckillGoodsInfoMapper seckillGoodsInfoMapper;

    @RequestMapping("init")
    public String init(){
        return "system/backstage/goods_list";
    }

    @RequestMapping("listByTable")
    @ResponseBody
    public RespBean ListByTable(GoodsInfo goodsInfo){
        return goodsInfoService.listByTable(goodsInfo);
    }

    //下架商品
    @RequestMapping("removeById")
    @ResponseBody
    public RespBean removeById(int id){
        boolean b = goodsInfoService.removeById(id);
        if (!b){
            return RespBean.error(RespBeanEnum.GOODS_DEL_ERROR);
        }
        return RespBean.success("下架成功");
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(GoodsInfo goodsInfo){
        return goodsInfoService.insert(goodsInfo);
    }

    @RequestMapping("updateById")
    @ResponseBody
    public RespBean updateById(GoodsInfo goodsInfo){
        boolean b = goodsInfoService.updateById(goodsInfo);
        if (!b){
            return RespBean.error(RespBeanEnum.GOODS_UPDATE_ERROR);
        }
        return RespBean.success("更新成功");
    }

    //详情页面的渲染
    @RequestMapping("/detail/{goodsId}")
    @ResponseBody
    public RespBean toDetail(@PathVariable Long goodsId,UserInfo userInfo) {
        SeckillGoodsInfoVo seckillGoodsInfoVo = seckillGoodsInfoMapper.findGoodsVoByGoodsId(goodsId);
        Date startDate = seckillGoodsInfoVo.getStartDate();
        Date endDate = seckillGoodsInfoVo.getEndDate();
        Date nowDate = new Date();
        int seckillStatus = 0;
        int remainSeconds = 0;
        //秒杀还未开始
        if (nowDate.before(startDate)) {
            remainSeconds = (int) ((startDate.getTime() - nowDate.getTime()) / 1000);
        } else if (nowDate.after(endDate)) {
            seckillStatus = 2;
            remainSeconds = -1;
        } else {
            seckillStatus = 1;
            remainSeconds = 0;
        }

        DetailVo detailVo = new DetailVo();
        detailVo.setUser(userInfo);
        detailVo.setSeckillGoodsInfoVo(seckillGoodsInfoVo);
        detailVo.setSeckillStatus(seckillStatus);
        detailVo.setRemainSeconds(remainSeconds);

        return RespBean.success(detailVo);

    }
}
