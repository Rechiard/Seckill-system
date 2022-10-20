package e.commerce.haowu.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import e.commerce.haowu.frame.utils.CookieUtil;
import e.commerce.haowu.system.entity.GoodsInfo;
import e.commerce.haowu.system.entity.OrderInfo;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.service.GoodsInfoService;
import e.commerce.haowu.system.service.OrderInfoService;
import e.commerce.haowu.system.service.SeckillGoodsInfoService;
import e.commerce.haowu.system.service.UserInfoService;
import e.commerce.haowu.system.vo.SeckillGoodsInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class JumpController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private GoodsInfoService goodsInfoService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private SeckillGoodsInfoService seckillGoodsInfoService;

    public UserInfo getUserInfo(HttpServletRequest request, HttpServletResponse response){
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        System.out.println(userTicket);
        UserInfo user = userInfoService.getUserByCookie(userTicket, request, response);
        return user;
    }

    @RequestMapping({"/toIndex","/"})
    public String toIndex(Model model,HttpServletRequest request,HttpServletResponse response){
        UserInfo user = getUserInfo(request,response);
        if(user != null){
            model.addAttribute("nickname",user.getNickname());
            if (user.getRole() == 2){
                model.addAttribute("admin",user.getRole());
            }
        }
        List<SeckillGoodsInfoVo> seckillGoodsInfoVos = seckillGoodsInfoService.listSeckillGoods();
        for (SeckillGoodsInfoVo seckillGoodsInfoVo : seckillGoodsInfoVos) {
            String text = seckillGoodsInfoVo.getGoodsDetail();
            if (text.length() >40){
                text = text.substring(0,39) + "...";
            }
            seckillGoodsInfoVo.setGoodsDetail(text);
        }
        if (seckillGoodsInfoVos != null){
            model.addAttribute("seckills",seckillGoodsInfoVos);
        }

        return "system/index";
    }

    @RequestMapping("/toCart")
    public String toCart(Model model,HttpServletRequest request,HttpServletResponse response){
        UserInfo user = getUserInfo(request,response);
        if(user != null){
            model.addAttribute("nickname",user.getNickname());
            model.addAttribute("userInfo",user);
            if (user.getRole() == 2){
                model.addAttribute("admin",user.getRole());
            }
        }

        List<OrderInfo> orderInfos = orderInfoService.selectByUserId(user.getId());
        int totalCount = 0;
        double totalPrice = 0;
        for (OrderInfo orderInfo : orderInfos) {
            totalCount += orderInfo.getGoodsCount();
            totalPrice += orderInfo.getGoodsPrice()*orderInfo.getGoodsCount();
        }
        model.addAttribute("orderInfos",orderInfos);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalPrice",totalPrice);

        return "system/cart";
    }

    @RequestMapping("/toLoginOrRegister")
    public String toLoginOrRegister(HttpServletRequest request,HttpServletResponse response){
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        if (!StringUtils.isBlank(userTicket)){
            redisTemplate.delete("user:"+userTicket);
            CookieUtil.deleteCookie(request,response,"userTicket");
        }
        return "system/login-register";
    }

    @RequestMapping("/toShopList")
    public String toshopList(@RequestParam("type")String type, Model model, HttpServletRequest request, HttpServletResponse response){
        UserInfo user = getUserInfo(request,response);
        if(user != null){
            model.addAttribute("nickname",user.getNickname());
            if (user.getRole() == 2){
                model.addAttribute("admin",user.getRole());
            }
        }
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsType(Integer.parseInt(type));
        goodsInfo.setLimit(20);
        goodsInfo.setPage(1);
        List<GoodsInfo> goodsInfos = goodsInfoService.listByType(goodsInfo);
        model.addAttribute("goodsInfos",goodsInfos);
        return "system/shop-list";
    }

    @RequestMapping("/toSingleGood")
    public String toSingleGood(@RequestParam("goodsId")String goodsId, Model model,HttpServletRequest request,HttpServletResponse response){
        UserInfo user = getUserInfo(request,response);
        if(user != null){
            model.addAttribute("nickname",user.getNickname());
            model.addAttribute("userInfo",user);
            if (user.getRole() == 2){
                model.addAttribute("admin",user.getRole());
            }
        }

        GoodsInfo goodsInfo = goodsInfoService.selectById(Long.parseLong(goodsId));
        model.addAttribute("goodsInfo",goodsInfo);

        //如果为空，则手动渲染，存入redis,非常重要的优化方式
       /* WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = resolver.getTemplateEngine().process("goodsList", webContext);
        if (!StringUtils.isBlank(html)) {
            valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
        }*/

        return "system/single-good";
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request,HttpServletResponse response){
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        //从redis中退出登录
        redisTemplate.delete("user:" + userTicket);
        //从Cookie中退出登录
        return "redirect:/";
    }

    @RequestMapping("/toMyAccount")
    public String toMyAccount(Model model,HttpServletRequest request,HttpServletResponse response){
        UserInfo user = getUserInfo(request,response);
        System.out.println("修改信息后我重新获取用户为"+user);
        if(user != null){
            model.addAttribute("userInfo",user);
            model.addAttribute("nickname",user.getNickname());
            if (user.getRole() == 2){
                model.addAttribute("admin",user.getRole());
            }
        }
        return "system/my-account";
    }

    //-----------------------------------------------------------------------------------------------------

    @RequestMapping("/toBackIndex")
    public String toBackIndex(HttpServletRequest request,HttpServletResponse response,Model model){
        UserInfo userInfo = getUserInfo(request, response);
        if (userInfo != null){
            model.addAttribute("nickname",userInfo.getNickname());
        }
        return "system/backstage/index";
    }

    @RequestMapping("/toWelcome")
    public String toWelcome(){

        return "system/backstage/welcome";
    }

    @RequestMapping("/RabbitMQInit")
    public String toRabbitMQ(){
        return "redirect:http://localhost:15672";
    }








}
