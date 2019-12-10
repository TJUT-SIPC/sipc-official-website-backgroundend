package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Projects;
import com.sipc115.catalina.repository.ProjectRepository;
import com.sipc115.catalina.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * 通过id查询一个项目
     * @param projectId 项目id
     * @return 项目对象
     */
    @Override
    public Projects findOne(Integer projectId) {
        return projectRepository.findOne(projectId);
    }

    /**
     * 分页查询所有项目
     * @param pageNum 页数
     * @param pageSize 一页显示多少条
     * @return 查询到的项目集合
     */
    @Override
    public List<Projects> findAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Projects> page = projectRepository.findAll(pageable);
        return page.getContent();
    }

    /**
     * 更新项目信息
     * @param project 项目对象
     * @return 若成功返回1
     */
    @Override
    public int updateProject(Projects project) {
        return projectRepository.updateProjectById(project.getProjectId(),project.getProjectDescription(),
                project.getProjectTime(),project.getProjectImageRaw(),project.getProjectImageCompress());
    }

    /**
     * 添加新项目
     * @param project 项目对象
     * @return 项目对象
     */
    @Override
    public Projects addProject(Projects project) {
        return projectRepository.save(project);
    }

    /**
     * 通过id删除一个项目
     * @param projectId 项目id
     */
    @Override
    public void delProject(Integer projectId) {
        projectRepository.delete(projectId);
    }
}
