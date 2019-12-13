package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.service.LogoutService;
import com.sipc115.catalina.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private RedisService redisService;

    @Override
    public void logout(String token) {
        redisService.remove(token);
    }
}
