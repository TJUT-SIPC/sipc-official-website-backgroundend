package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Projects;
import com.sipc115.catalina.enums.ResultEnum;
import com.sipc115.catalina.exception.BusinessException;
import com.sipc115.catalina.repository.ProjectRepository;
import com.sipc115.catalina.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * 1.通过id查询一个项目
     * @param projectId 项目id
     * @return 项目对象
     */
    @Override
    public Projects findOne(Integer projectId) {
        try{
            return projectRepository.findById(projectId).get();
        }catch (NoSuchElementException e){
            log.error("通过项目id查找不到项目,id={}",projectId);
            throw new BusinessException(ResultEnum.PROJECT_ID_NOT_EXIST);
        }

    }

    /**
     * 2.分页查询所有项目
     * @param pageNum 页数
     * @param pageSize 一页显示多少条
     * @return 查询到的项目集合
     */
    @Override
    public Page<Projects> findAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Projects> page = projectRepository.findAll(pageable);
        return page;
    }

    /**
     * 3.更新项目信息
     * @param project 项目对象
     * @return 若成功返回1
     */
    @Override
    public int updateProject(Projects project) {
        return projectRepository.updateProjectById(project.getProjectId(),project.getProjectDescription(),
                project.getProjectTime(),project.getProjectImageRaw(),project.getProjectImageCompress());
    }

    /**
     * 4.添加新项目
     * @param project 项目对象
     * @return 项目对象
     */
    @Override
    public Projects addProject(Projects project) {
        return projectRepository.save(project);
    }

    /**
     * 5.通过id删除一个项目
     * @param projectId 项目id
     */
    @Override
    public void delProject(Integer projectId) {
        projectRepository.deleteById(projectId);
    }
}
