package e.commerce.haowu.frame.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class RespBean {

    private int code;
    private String message;
    private Object data;
    private int count;

    public static RespBean success() {
        return new RespBean().setCode(RespBeanEnum.SUCCESS.getCode()).setMessage(RespBeanEnum.SUCCESS.getMessage()).setData(null);
    }

    public static RespBean success(Object data) {
        return new RespBean().setCode(RespBeanEnum.SUCCESS.getCode()).setMessage(RespBeanEnum.SUCCESS.getMessage()).setData(data);
    }

    public static RespBean error(RespBeanEnum respBeanEnum) {
        return new RespBean().setCode(respBeanEnum.getCode()).setMessage(respBeanEnum.getMessage()).setData(null);
    }

    public static RespBean error(RespBeanEnum respBeanEnum, Object data) {
        return new RespBean().setCode(respBeanEnum.getCode()).setMessage(respBeanEnum.getMessage()).setData(data);
    }

    public static RespBean table(int count,Object data){
        return new RespBean().setCode(0).setCount(count).setMessage("查询成功").setData(data);
    }
}
