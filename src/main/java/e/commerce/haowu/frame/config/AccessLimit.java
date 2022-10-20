package e.commerce.haowu.frame.config;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拦截器，限流判断
 */
@Retention(RetentionPolicy.RUNTIME)    //运行时
@Target(ElementType.METHOD)
public @interface AccessLimit {

    int second();

    int maxCount();

    boolean needLogin() default true;

}
