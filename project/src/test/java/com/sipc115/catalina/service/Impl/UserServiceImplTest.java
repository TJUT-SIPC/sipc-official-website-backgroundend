package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void findAllTest() {
        System.out.println(userService.findAll(2,1));
    }

    @Test
    public void saveTest(){
        Users user = new Users();
        user.setUserId(2);
        user.setUserName("娃哈哈牛奶");
        user.setUserPassword("456123");
        user.setUserEmail("564110032@qq.com");

        userService.addUser(user);
    }

    @Test
    public void delTest(){
        userService.delUser(4);
    }
}