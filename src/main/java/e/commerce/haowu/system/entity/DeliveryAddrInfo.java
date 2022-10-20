package e.commerce.haowu.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import e.commerce.haowu.frame.dto.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author bobo
 * @since 2022-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("delivery_addr_info")
@ApiModel(value="DeliveryAddrInfo对象", description="")
public class DeliveryAddrInfo extends BaseEntity {


    @ApiModelProperty(value = "地址ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID，（电话号码）")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "用户地址")
    @TableField("user_delivery_addr")
    private String userDeliveryAddr;

    @ApiModelProperty(value = "联系人")
    @TableField("user_nickname")
    private String userNickname;

    @ApiModelProperty(value = "手机号码")
    @TableField("user_mobile")
    private String userMobile;

    @ApiModelProperty(value = "状态，1为正常，2为删除")
    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status;

}
