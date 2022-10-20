package e.commerce.haowu.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.dto.RespBeanEnum;
import e.commerce.haowu.frame.utils.CookieUtil;
import e.commerce.haowu.frame.utils.MD5Util;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Controller
@RequestMapping("/system/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    public UserInfo getUserInfo(HttpServletRequest request, HttpServletResponse response){
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        UserInfo user = userInfoService.getUserByCookie(userTicket, request, response);
        return user;
    }


    @RequestMapping("/doRegister")
    @ResponseBody
    public RespBean doRegister(@RequestParam("id")long id,
                               @RequestParam("email")String email,
                               @RequestParam("nickname")String nickname,
                               @RequestParam("password")String password,
                               @RequestParam("repassword")String rePassword, HttpServletRequest request, HttpServletResponse response){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setEmail(email);
        userInfo.setNickname(nickname);
        userInfo.setPassword(password);
        System.out.println(rePassword);
        if(!userInfo.getPassword().equals(rePassword)){
            return RespBean.error(RespBeanEnum.PASSWORD_DIFFERENT);
        }
        return userInfoService.doRegister(userInfo,request,response);
    }

    @RequestMapping("/saveEntity")
    @ResponseBody
    public RespBean saveEntity(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response){
        return userInfoService.saveEntity(userInfo,request,response);
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public RespBean updatePwd(@RequestParam("id")Long id,
                              @RequestParam("oldPassword")String oldPassword,
                              @RequestParam("newPassword1")String newPassword1,
                              @RequestParam("newPassword2")String newPassword2){
        if (!newPassword2.equals(newPassword1)){
            return RespBean.error(RespBeanEnum.PASSWORD_DIFFERENT);
        }
        return userInfoService.updatePwd(id,oldPassword,newPassword1);
    }

    @RequestMapping("init")
    public String init(){
        return "system/backstage/users_list";
    }

    @RequestMapping("listByTable")
    @ResponseBody
    public RespBean ListByTable(int page,int limit){
        System.out.println(page);
        System.out.println(limit);
        UserInfo userInfo = new UserInfo();
        userInfo.setPage(page);
        userInfo.setLimit(limit);
        return userInfoService.listByTable(userInfo);
    }

}
