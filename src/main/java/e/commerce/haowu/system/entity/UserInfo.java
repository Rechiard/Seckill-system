package e.commerce.haowu.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.fasterxml.jackson.annotation.JsonFormat;
import e.commerce.haowu.frame.dto.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("user_info")
@ApiModel(value="UserInfo对象", description="")
public class UserInfo extends BaseEntity {


    @ApiModelProperty(value = "用户ID，手机号码")
    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "用户名")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "盐值")
    @TableField("salt")
    private String salt;


    @ApiModelProperty(value = "邮箱信息")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "1为用户，2为管理员")
    @TableField("role")
    private int role;


    @ApiModelProperty(value = "注册时间")
    @TableField("register_date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp registerDate;

    @ApiModelProperty(value = "状态，1为成功，2为删除")
    @TableField(value = "status", fill = FieldFill.INSERT)
    private Integer status;


}
