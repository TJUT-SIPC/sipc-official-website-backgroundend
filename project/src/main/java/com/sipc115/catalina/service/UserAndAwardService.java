package com.sipc115.catalina.service;

import org.springframework.stereotype.Service;

@Service
public interface UserAndAwardService {

    /**通过用户id删除其 用户_奖项 中间表对应关系*/
    void delRelationByUserId(Integer userId);

    /**通过奖项id删除 用户_奖项 中间表对应关系*/
    void delRelationByAwardId(Integer awardId);
}
