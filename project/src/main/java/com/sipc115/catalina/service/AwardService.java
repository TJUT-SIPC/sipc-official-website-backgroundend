package com.sipc115.catalina.service;

import com.sipc115.catalina.dataobject.Awards;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface AwardService {

    /**单个奖项查询*/
    Awards findOne(Integer awardId);

    /**分页查询奖项*/
    Page<Awards> findAll(Integer pageNum, Integer pageSize);

    /**修改奖项*/
    int updateAward(Awards award);

    /**添加奖项*/
    Awards addAward(Awards award);

    /**删除奖项*/
    void delAward(Integer awardId);



}
