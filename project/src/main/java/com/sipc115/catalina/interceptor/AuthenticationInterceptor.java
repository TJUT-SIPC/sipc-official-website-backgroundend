package com.sipc115.catalina.interceptor;

import com.alibaba.fastjson.JSON;
import com.sipc115.catalina.annotation.LoginRequired;
import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.service.RedisService;
import com.sipc115.catalina.service.UserService;
import com.sipc115.catalina.utils.TokenUtil;
import com.sipc115.catalina.configuration.UserConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    public final static String ACCESS_TOKEN = UserConstants.ACCESS_TOKEN_HEAD;

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;


    //请求处理后开始调用，在视图渲染前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //整个请求结束后调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    //在业务处理器处理请求之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);

        //有@LoginRequired注解，需要认证
        if(methodAnnotation != null){

            //判断是否存在令牌信息，若存在则允许登录
            String accessToken = request.getHeader(ACCESS_TOKEN);

            if(null == accessToken){
                throw new Exception("没有令牌信息");
            }else {

                //从Redis中查看token是否存在
                String token = redisService.get(UserConstants.REDIS_USER + accessToken);

                if(token == null){
                    throw new Exception("用户未登录");
                }

                Claims claims = null;

                try{
                    claims = TokenUtil.parseJWT(accessToken);
                }catch (ExpiredJwtException e){
                    response.setStatus(401);
                    throw new Exception("未知错误 ExpiredJwtException");
                }catch (SignatureException e){
                    response.setStatus(401);
                    throw new Exception("未知错误 SignatureException");
                }catch (Exception e){
                    response.setStatus(401);
                    throw new Exception("未知错误");
                }

                //根据用户名查找用户
                Users user = new Users();
                user = userService.findOneByUserName(claims.getId());

                //设置session
                HttpSession session = request.getSession();
                session.setAttribute("userId",user.getUserId());

                if(user == null){
                    response.setStatus(401);
                    throw new Exception("不存在此用户");
                }
                String userJson = redisService.get(UserConstants.REDIS_USER + accessToken).toString();

                //从Redis中获取用户信息
                User user1;
                user1 = JSON.parseObject(userJson, User.class);
                if(userJson!=null && userJson!="" && user1!=null){
                    //当前登录用户@CurrentUser
                    request.setAttribute(UserConstants.CURRENT_USER, user1);
                }else {
                    throw new Exception("用户信息出现问题");
                }
                return true;
            }

        }else{
            //不需登录可请求
            return true;
        }

    }

}
