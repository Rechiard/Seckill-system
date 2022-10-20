package e.commerce.haowu.system.controller;


import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.dto.RespBeanEnum;
import e.commerce.haowu.system.entity.DeliveryAddrInfo;
import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.entity.OrderInfo;
import e.commerce.haowu.system.service.GoodsInfoService;
import e.commerce.haowu.system.service.OrderInfoService;
import e.commerce.haowu.system.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Controller
@RequestMapping("/system/orderInfo")
public class OrderInfoController {

    @Autowired
    OrderInfoService orderInfoService;

    //保存订单
    @RequestMapping("/save")
    @ResponseBody
    public RespBean save(OrderInfo entity){
        System.out.println("前端得到的订单为：" + entity);

        return orderInfoService.saveEntity(entity);
    }

    //删除订单
    @RequestMapping("/delOrder")
    @ResponseBody
    public RespBean delOrder(int id){
        orderInfoService.removeById(id);
        return RespBean.success("删除成功");
    }

    //支付
    @RequestMapping("/pay")
    @ResponseBody
    public RespBean pay(DeliveryAddrInfo entity){
        System.out.println("DeliveryAddrInfo : " + entity);
        return orderInfoService.updateDeliveryAddrByUserId(entity.getUserId(),entity.getId());
    }

    //订单页面
    @RequestMapping("init")
    public String init(){
        return "system/backstage/orders_list";
    }

    //历史页面
    @RequestMapping("initHistory")
    public String initHistory(){
        return "system/backstage/history_list";
    }

    //订单列表
    @RequestMapping("listByTable")
    @ResponseBody
    public RespBean ListByTable(OrderVo entity){
        return orderInfoService.listByTable(entity);
    }

    //历史订单列表
    @RequestMapping("listHistory")
    @ResponseBody
    public RespBean listHistory(OrderVo entity){
        return orderInfoService.listHistory(entity);
    }

    //撤销订单
    @RequestMapping("/deleteById")
    @ResponseBody
    public RespBean deleteById(@RequestParam("id") int id){
        System.out.println("1231232131");
        boolean b = orderInfoService.removeById(id);
        if (!b){
            return RespBean.error(RespBeanEnum.ORDER_RETURN_ERROR);
        }
        return RespBean.success("订单撤销成功");
    }

    //发货
    @RequestMapping("/updateById")
    @ResponseBody
    public RespBean updateById(@RequestParam("id")int id){
        return orderInfoService.updateStatusById(id);
    }

}
