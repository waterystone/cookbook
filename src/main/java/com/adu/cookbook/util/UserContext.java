package com.adu.cookbook.util;

import com.adu.cookbook.model.UserInfo;

/**
 * 用户上下文信息
 */
public class UserContext {
    private static final ThreadLocal<UserInfo> userInfoLocal = new ThreadLocal<UserInfo>();

    public static UserInfo getUserInfo() {
        UserInfo res = userInfoLocal.get();
        return res;
    }

    public static void setUserInfo(UserInfo userInfo) {
        userInfoLocal.set(userInfo);
    }

    public static void clear() {
        userInfoLocal.remove();
    }


}
