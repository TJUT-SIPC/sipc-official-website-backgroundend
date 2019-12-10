package com.sipc115.catalina.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WishServiceImplTest {

    @Autowired
    private WishServiceImpl wishService;

    @Test
    public void findAll() {
        System.out.println("查询到："+wishService.findAll(2,3));
    }

    @Test
    public void findWishesByWishStatus() {
        System.out.println("查询到特定状态寄语："+wishService.findWishesByWishStatus(1,1,1));
    }

    @Test
    public void findWishesByLimit() {
        System.out.println("随机查询："+wishService.findWishesByLimit(6));
    }

    @Test
    public void updateWishStatus() {
        wishService.updateWishStatus(7,3);
    }

    @Test
    public void delWish() {
        wishService.delWish(9);
    }
}