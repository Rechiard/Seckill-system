package e.commerce.haowu.frame.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//此注解是自定义的检验方法
@Constraint(validatedBy = {isMobileValidator.class})
//自定义注解，用于检验参数是否正确，起作用在于省去了检验参数的重复代码，而是变成了注解的方式进行校验参数，效果可见LoginVo
public @interface IsMobile {

    boolean required() default true;

    String message() default "手机号码格式错误！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
