package com.sipc115.catalina.utils;

import javax.servlet.http.HttpServletRequest;

public class URLUtil {

    public static String getLocalhostURL(HttpServletRequest request){
        //获取当前请求全路径 http://localhost:8082/xxx
        String requestURL =  request.getRequestURL().toString();
        //获取服务器地址 http://localhost:8082/
        String localhostURL = requestURL.substring(0,requestURL.lastIndexOf(request.getRequestURI()) + 1);

        return localhostURL;
    }

}
