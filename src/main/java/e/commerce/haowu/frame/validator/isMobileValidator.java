package e.commerce.haowu.frame.validator;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import e.commerce.haowu.frame.utils.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class isMobileValidator implements ConstraintValidator<IsMobile, String> {


    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    //判断输入的参数是否是必须要填写的
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //如果是需要填写的,则进行用ValidatorUtil进行判断是否是正确的格式
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            //如果不是必要填写的，则判断是否是空
            if (StringUtils.isBlank(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
