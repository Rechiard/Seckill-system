package e.commerce.haowu.system.vo;

import e.commerce.haowu.frame.validator.IsMobile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class LoginVo {

    @NotNull
    //此注解是自定义的注解，用于判断手机号码是否正确
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

}
