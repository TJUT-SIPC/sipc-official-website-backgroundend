package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.repository.UserAndAwardRepository;
import com.sipc115.catalina.service.UserAndAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAndAwardServiceImpl implements UserAndAwardService {

    @Autowired
    private UserAndAwardRepository repository;

    /**
     * 1.通过用户id删除其关联的所有奖项
     * @param userId
     */
    @Override
    public void delAwardByUserId(Integer userId) {
        repository.delete(userId);
    }
}
