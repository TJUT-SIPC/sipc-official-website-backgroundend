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
     * 1.通过奖项id删除 用户_奖项 中间表对应关系
     * @param userId    用户id
     */
    @Override
    public void delRelationByUserId(Integer userId) {
        repository.deleteByUserId(userId);
    }

    /**
     * 2.通过奖项id删除 用户_奖项 中间表对应关系
     * @param awardId   奖项id
     */
    @Override
    public void delRelationByAwardId(Integer awardId) {
        repository.deleteByAwardId(awardId);
    }
}
