package com.sipc115.catalina.service;

import com.sipc115.catalina.dataobject.Dynamics;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface DynamicService {

    /**单个动态查询*/
    Dynamics findOne(Integer dynamicId);

    /**分页查询动态*/
    Page<Dynamics> findAll(Integer pageNum, Integer pageSize);

    /**修改动态*/
    int updateDynamic(Dynamics dynamic);

    /**添加动态*/
    Dynamics addDynamic(Dynamics dynamic);

    /**删除动态*/
    void delDynamic(Integer dynamicId);

}
