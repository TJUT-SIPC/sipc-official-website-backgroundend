package com.sipc115.catalina.service;

import com.sipc115.catalina.dataobject.Users;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    /**登录方法，返回token*/
    String login(Users user);

}
