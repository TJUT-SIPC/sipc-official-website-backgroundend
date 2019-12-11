package com.sipc115.catalina.service;

import org.springframework.stereotype.Service;

@Service
public interface UserAndAwardService {

    /**通过用户id删除其关联的所有奖项*/
    void delAwardByUserId(Integer userId);

}
