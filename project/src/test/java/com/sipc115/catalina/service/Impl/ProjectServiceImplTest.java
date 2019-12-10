package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Projects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {

    @Autowired
    private ProjectServiceImpl projectService;

    @Test
    public void findOne() {
        System.out.println("查询到结果："+projectService.findOne(1));
    }

    @Test
    public void findAll() {
        System.out.println(projectService.findAll(2,1));
    }

    @Test
    public void updateProject() {
        Projects project = new Projects();
        project.setProjectId(2);
        project.setProjectDescription("游泳");
        project.setProjectTime(new Date());
        project.setProjectImageRaw("lala");
        project.setProjectImageCompress("lulu");
        projectService.updateProject(project);
    }

    @Test
    public void addProject() {
        Projects project = new Projects();
        project.setProjectId(5);
        project.setProjectDescription("登山");
        project.setProjectTime(new Date());
        project.setProjectImageRaw("原图");
        project.setProjectImageCompress("缩略图");
        projectService.addProject(project);
    }

    @Test
    public void delProject() {
        projectService.delProject(2);
    }
}