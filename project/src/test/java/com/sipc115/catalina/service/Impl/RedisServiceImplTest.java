package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceImplTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void set() {

    }

    @Test
    public void testSet() {
    }

    @Test
    public void get() {
        redisService.set("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhZG1pbiIsImlhdCI6MTU3NjIzNjYwMCwic3ViIjoiIiwiaXNzIjoic2lwYzExNS5jb20iLCJleHAiOjE1NzY0NTI2MDB9.xsPqUg8D-LbrySn7ZzNTj7iwp2y49aEjGc4erniCJAI","220",(long)3600*60);
        System.out.println(redisService.get("NS5jb20iLCJleHAiOjE1NzY0NTI2MDB9.xsPqUg8D-LbrySn7ZzNTj7iwp2y49aEjGc4erniCJAI"));
    }
}