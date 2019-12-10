package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Awards;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwardServiceImplTest {

    @Autowired
    private AwardServiceImpl awardService;

    @Test
    public void findOne() {
        System.out.println("查询到奖项：" + awardService.findOne(3));
    }

    @Test
    public void findAll() {
        System.out.println("分页"+awardService.findAll(1,2));
    }

    @Test
    public void updateAward() {
        Awards award = awardService.findOne(1);
        award.setAwardName("语文朗读比赛");
        award.setAwardTime(new Date());

        awardService.updateAward(award);
    }

    @Test
    public void addAward() {
        Awards award = new Awards();
        award.setAwardId(12);
        award.setAwardName("华北五省二等奖");
        award.setAwardTime(new Date());

        awardService.addAward(award);
    }

    @Test
    public void delAward() {
        awardService.delAward(12);
    }
}