package e.commerce.haowu.system.vo;


import e.commerce.haowu.system.entity.OrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class OrderVo extends OrderInfo {

    private String userNickname;

    private String userDeliveryAddr;

    private String userMobile;

}
