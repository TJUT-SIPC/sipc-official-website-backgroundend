package com.sipc115.catalina.service.Impl;

import com.alibaba.fastjson.JSON;
import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.service.LoginService;
import com.sipc115.catalina.service.RedisService;
import com.sipc115.catalina.utils.TokenUtil;
import com.sipc115.catalina.configuration.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private HttpSession session;

    @Override
    public String login(Users user) {
        //生成token
        String accessToken = TokenUtil.createJwtToken(user.getUserName());

        //写入session
        session.setAttribute("userId",user.getUserId());

        //写入redis
        redisService.set(UserConstants.REDIS_USER + accessToken, JSON.toJSONString(user) , UserConstants.REDIS_TIME);

        //返回token给前端
        return accessToken;
    }
}
