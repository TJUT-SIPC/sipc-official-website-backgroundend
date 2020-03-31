package com.sipc115.catalina.service;

import com.sipc115.catalina.dataobject.Projects;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 项目
 */
@Service
public interface ProjectService {

    /**单个项目查询*/
    Projects findOne(Integer projectId);

    /**分页查询所有项目*/
    Page<Projects> findAll(Integer pageNum, Integer pageSize);

    /**修改项目*/
    int updateProject(Projects project);

    /**添加项目*/
    Projects addProject(Projects project);

    /**删除项目*/
    void delProject(Integer projectId);

}
