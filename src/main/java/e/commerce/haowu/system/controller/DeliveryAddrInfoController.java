package e.commerce.haowu.system.controller;


import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.utils.CookieUtil;
import e.commerce.haowu.system.entity.DeliveryAddrInfo;
import e.commerce.haowu.system.service.DeliveryAddrInfoService;
import e.commerce.haowu.system.service.UserInfoService;
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
@RequestMapping("/system/deliveryAddrInfo")
public class DeliveryAddrInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DeliveryAddrInfoService deliveryAddrInfoService;

    @RequestMapping("/listByTable")
    @ResponseBody
    public RespBean listByTable(DeliveryAddrInfo entity){
        return deliveryAddrInfoService.listByTable(entity);
    }

    @RequestMapping("/save")
    @ResponseBody
    public RespBean save(DeliveryAddrInfo entity){
        System.out.println("新增地址得到的地址为：" + entity);
        return deliveryAddrInfoService.saveEntity(entity);
    }

    @RequestMapping("updateById")
    @ResponseBody
    public RespBean updateById(DeliveryAddrInfo entity){
        return deliveryAddrInfoService.updateEntity(entity);
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public RespBean deleteById(int id){
        deliveryAddrInfoService.removeById(id);
        return RespBean.success("删除成功");
    }

}
