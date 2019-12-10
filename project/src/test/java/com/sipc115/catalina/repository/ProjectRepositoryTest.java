package com.sipc115.catalina.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository repository;

    @Test
    public void findOne() {
        System.out.println(repository.findOne(2));
    }

    @Test
    public void updateTest(){
        repository.updateProjectById(2,"游泳",new Date(),
                "lala","haha");
    }
}