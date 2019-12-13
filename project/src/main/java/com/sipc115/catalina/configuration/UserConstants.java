package com.sipc115.catalina.configuration;

public class UserConstants {

    /**当前用户参数名*/
    public final static String CURRENT_USER = "CurrentUser";
    /**Redis中缓存的用户信息前缀*/
    public final static String REDIS_USER = "USER_";
    /**token的请求头Header*/
    public final static String ACCESS_TOKEN_HEAD = "sipc-token";
    /**若用户点击下次登录，设置redis七天有效期(单位毫秒)*/
    public final static int REDIS_TIME = 1000*60*60*24*7;

}
