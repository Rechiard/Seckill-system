package e.commerce.haowu.system.service;

import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.system.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import e.commerce.haowu.system.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
public interface UserInfoService extends IService<UserInfo> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    UserInfo getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response);

    RespBean doRegister(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response);

    RespBean saveEntity(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response);

    RespBean updatePwd(Long id,String oldPassword,String newPassword);

    RespBean loginBackStage(String username, String password);

    RespBean listByTable(UserInfo userInfo);
}
