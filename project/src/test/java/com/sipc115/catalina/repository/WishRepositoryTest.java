package com.sipc115.catalina.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WishRepositoryTest {

    @Autowired
    private WishRepository repository;

    @Test
    public void findAll() {
        System.out.println("查询到多少条记录"+repository.findWishesByWishStatus(1,
                PageRequest.of(0,2)
        ).getTotalElements());
    }

    @Test
    public void findWishesByLimit() {
        System.out.println("查询到"+repository.findWishesByLimit(10));
    }

    @Test
    public void updateWishStatus() {
        repository.updateWishStatus(6,2);
    }

    @Test
    public void delete() {
        repository.deleteById(5);
    }
}