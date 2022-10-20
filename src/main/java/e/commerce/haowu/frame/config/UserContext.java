package e.commerce.haowu.frame.config;


import e.commerce.haowu.system.entity.UserInfo;

/**
 * 避免线程安全
 * 需要详细了解
 */
public class UserContext {

    private static ThreadLocal<UserInfo> userHolder = new ThreadLocal<>();

    public static void setUser(UserInfo user) {
        userHolder.set(user);
    }

    public static UserInfo getUser() {
        return userHolder.get();
    }

}
