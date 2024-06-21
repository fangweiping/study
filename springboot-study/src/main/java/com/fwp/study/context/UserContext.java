package com.fwp.study.context;

import com.fwp.study.vo.User;

/**
 * 用户上下文,通过ThreadLocal存储用户信息
 */
public class UserContext {

    public static ThreadLocal<User> userLocal = new ThreadLocal<>();

    public static User getUser() {
        return userLocal.get();
    }

    public static void setUser(User user) {
        userLocal.set(user);
    }

    public static void removeUser() {
        userLocal.remove();
    }
}
