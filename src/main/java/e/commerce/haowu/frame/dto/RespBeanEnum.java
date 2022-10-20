package e.commerce.haowu.frame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    //通用
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),
    //登录模块
    LOGIN_ERROR(500210, "用户名或密码不正确"),
    MOBILE_ERROR(500211, "手机号码格式不正确"),
    BIND_ERROR(500212, "参数校验异常"),
    MOBILE_NOT_EXIST(500213, "手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500214, "更新密码失败"),
    SESSION_ERROR(500215, "用户不存在"),
    USER_INFO_EXIST(500216,"用户已存在"),
    REGISTER_ERROR(500217,"注册失败"),
    PASSWORD_DIFFERENT(500218,"两次密码输入不一致"),
    OLD_PASSWORD_ERROR(500219,"旧密码输入错误"),
    ROLE_ERROR(500220,"您不是管理员，无法访问后台"),
    PASSWORD_ERROR(500221,"密码输入错误"),

    //商品模块
    GOODS_DEL_ERROR(500400,"商品下架失败"),
    GOODS_INSERT_ERROR(500400,"商品新增失败"),
    GOODS_UPDATE_ERROR(500400,"商品更新失败"),

    //订单模块
    ORDER_NOT_EXIST(500300, "订单不存在"),
    USER_NOT_LOGIN(500301, "请先登录"),
    STOCK_INSUFFICIENT(500301, "库存不足，无法购买"),
    BUY_ERROR(500301, "购买失败"),
    PAY_ERROR(500302, "支付失败"),
    ORDER_CHECK_OUT_ERROR(500303,"发货失败"),
    ORDER_RETURN_ERROR(500303,"订单撤销失败"),

    //地址模块
    DELIVERY_INSERT_ERROR(500400,"地址插入失败"),
    DELIVERY_UPDATE_ERROR(500401,"地址更新失败"),

    //秒杀模块
    EMPTY_STOCK(500500, "库存不足"),
    REPEAT_ERROR(500501, "该商品每人限购一件"),
    REQUEST_ILLEGAL(500502, "请求非法，请重新尝试"),
    ERROR_CAPTCHA(500503, "验证码错误，请重新输入"),
    ACCESS_LIMIT_REACHED(500504, "访问过于频繁，请稍后再试");


    private final Integer code;
    private final String message;
}
