package com.sipc115.catalina.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageBoardServiceImplTest {

    @Autowired
    private MessageBoardServiceImpl messageBoardService;

    @Test
    public void findOne() {
        System.out.println( messageBoardService.findOne(1));
    }

    @Test
    public void findAll() {
        System.out.println(messageBoardService.findAll(1,1));
    }

    @Test
    public void delMessage() {
        messageBoardService.delMessage(3);
    }
}