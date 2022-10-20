package e.commerce.haowu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import e.commerce.haowu.frame.dto.RespBean;
import e.commerce.haowu.frame.dto.RespBeanEnum;
import e.commerce.haowu.frame.exception.GlobalException;
import e.commerce.haowu.frame.utils.CookieUtil;
import e.commerce.haowu.frame.utils.MD5Util;
import e.commerce.haowu.frame.utils.UUIDUtil;
import e.commerce.haowu.system.entity.UserInfo;
import e.commerce.haowu.system.mapper.UserInfoMapper;
import e.commerce.haowu.system.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import e.commerce.haowu.system.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 登录
     * @param loginVo
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {

        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //根据手机号获取用户
        UserInfo user = userInfoMapper.selectById(mobile);

        if (user == null) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //判断密码是否正确
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //生成cookie
        String ticket = UUIDUtil.uuid();
        //将用户信息存入redis
        redisTemplate.opsForValue().set("user:" + ticket, user);
        //request.getSession().setAttribute(ticket,user);

        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }

    /**
     * 获取用户
     * @param userTicket
     * @param request
     * @param response
     * @return
     */
    @Override
    public UserInfo getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {

        if (StringUtils.isBlank(userTicket)) {
            return null;
        }

        UserInfo user = (UserInfo) redisTemplate.opsForValue().get("user:" + userTicket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", userTicket);
        }
        return user;
    }

    @Override
    public RespBean doRegister(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) {
        //查询user_info表中的所有id 是否和 添加的entity的id 有相同的
        List<UserInfo> existList = baseMapper.selectList(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId,userInfo.getId()));
        if(!CollectionUtils.isEmpty(existList)){
            return RespBean.error(RespBeanEnum.USER_INFO_EXIST);
        }

        userInfo.setSalt("1a2b3c4d");
        userInfo.setPassword(MD5Util.inputPassToDBPass(userInfo.getPassword(),userInfo.getSalt()));
        userInfo.setRegisterDate(new Timestamp(System.currentTimeMillis()));
        userInfo.setStatus(1);
        userInfo.setRole(1);
        System.out.println("注册的user为"+userInfo);
        int insert = userInfoMapper.insert(userInfo);
        if (insert <= 0 ){
            return RespBean.error(RespBeanEnum.REGISTER_ERROR);
        }
        String ticket = UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user:" + ticket,userInfo);
        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }

    @Override
    public RespBean saveEntity(UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) {
        System.out.println("修改信息得到的userInfo"+userInfo);
        int i = userInfoMapper.updateById(userInfo);
        if (i <= 0){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        String userTicket = CookieUtil.getCookieValue(request, "userTicket");
        redisTemplate.opsForValue().set("user:"+userTicket,userInfo);
        System.out.println("修改完成");
        return RespBean.success();
    }

    @Override
    public RespBean updatePwd(Long id, String oldPassword,String newPassword) {
        UserInfo userInfo = userInfoMapper.selectById(id);
        if (userInfo == null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        if (!userInfo.getPassword().equals(MD5Util.inputPassToDBPass(oldPassword,userInfo.getSalt()))){
            return RespBean.error(RespBeanEnum.OLD_PASSWORD_ERROR);
        }

        userInfo.setPassword(MD5Util.inputPassToDBPass(newPassword,userInfo.getSalt()));
        int i = userInfoMapper.updateById(userInfo);
        if (i <= 0){
            return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
        }
        return RespBean.success();
    }

    @Override
    public RespBean loginBackStage(String username, String password) {
        UserInfo userInfo = userInfoMapper.selectById(username);
        if (userInfo == null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        if (!userInfo.getPassword().equals(password)){
            return RespBean.error(RespBeanEnum.PASSWORD_ERROR);
        }
        if(userInfo.getRole() != 2){
            return RespBean.error(RespBeanEnum.ROLE_ERROR);
        }
        return RespBean.success();
    }

    @Override
    public RespBean listByTable(UserInfo userInfo) {
        return RespBean.table(baseMapper.countByTable(userInfo),baseMapper.listByTable(userInfo));
    }

}
