package com.yc.common.web.data;


/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 13:37 2021/2/24
 */
public class UserThreadLocal {
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User get(){
        return userThreadLocal.get();
    }

    public static void set(User user){
        userThreadLocal.set(user);
    }

    public static void remove(){
        userThreadLocal.remove();
    }
}

