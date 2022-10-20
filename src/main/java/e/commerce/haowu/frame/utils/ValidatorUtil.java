package e.commerce.haowu.frame.utils;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//手机校验，用于判断输入的是否是正确的手机号码
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }
}
