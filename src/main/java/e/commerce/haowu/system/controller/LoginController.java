package e.commerce.haowu.system.controller;


import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.system.service.UserInfoService;
import e.commerce.haowu.system.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        System.out.println(112233);
        return userInfoService.doLogin(loginVo,request,response);

    }

}
