package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Dynamics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicServiceImplTest {

    @Autowired
    private DynamicServiceImpl dynamicService;

    @Test
    public void findOne() {
        System.out.println(dynamicService.findOne(2));
    }

    @Test
    public void findAll() {
        System.out.println(dynamicService.findAll(2,1));
    }

    @Test
    public void updateDynamic() {
        Dynamics dynamic = dynamicService.findOne(5);
        dynamic.setDynamicImage("rrttyy");
        dynamicService.updateDynamic(dynamic);
    }

    @Test
    public void addDynamic() {
        Dynamics dynamic = new Dynamics();
        dynamic.setDynamicImage("旅游");
        dynamic.setDynamicHeader("今日头条");
        dynamic.setDynamicText("哇");
        dynamicService.addDynamic(dynamic);
    }

    @Test
    public void delDynamic() {
        dynamicService.delDynamic(2);
    }
}