package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void findAllTest(){
        Pageable pageable = new PageRequest(0,5);
        Page<Users> page = repository.findAll(pageable);
        System.out.println(page.getContent());
    }

    @Test
    public void saveTest(){
        Users user = new Users();
        user.setUserId(9);
        user.setUserEmail("29985986@qq.com");
        user.setUserName("好给力");
        user.setUserPassword("3550620a");

        System.out.println(repository.save(user));
    }

    @Test
    public void findOneTest(){
        System.out.println(repository.findOne(3));
    }

}