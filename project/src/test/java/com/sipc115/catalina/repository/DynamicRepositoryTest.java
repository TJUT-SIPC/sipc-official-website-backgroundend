package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.Dynamics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicRepositoryTest {

    @Autowired
    private DynamicRepository repository;

    @Test
    public void updateDynamicById() {
        Dynamics dynamic = new Dynamics();
        dynamic.setDynamicId(1);
        dynamic.setDynamicImage("123123");
        dynamic.setDynamicHeader("123");
        dynamic.setDynamicText("123");
        dynamic.setDynamicCategory(4);
        repository.updateDynamicById(1,"123","biaoti","haahah",
                "wuren",3);
    }

    @Test
    public void save() {
        Dynamics dynamic = new Dynamics();
        dynamic.setDynamicId(1);
        dynamic.setDynamicImage("天气真好");
        dynamic.setDynamicHeader("标题1");
        dynamic.setDynamicText("内容1");
        dynamic.setDynamicCategory(2);
        repository.save(dynamic);
    }
}