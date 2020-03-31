package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Awards;
import com.sipc115.catalina.repository.AwardRepository;
import com.sipc115.catalina.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AwardServiceImpl implements AwardService {

    @Autowired
    private AwardRepository awardRepository;

    /**
     * 1.通过id查询一个奖项
     * @param awardId 奖项id
     * @return 奖项对象
     */
    @Override
    public Awards findOne(Integer awardId) {
        return awardRepository.findById(awardId).get();
    }

    /**
     * 2.分页查询所有奖项(按时间排序)
     * @param pageNum 页数
     * @param pageSize 一页显示多少条
     * @return 查询到的奖项集合
     */
    @Override
    public Page<Awards> findAll(Integer pageNum, Integer pageSize) {
        //排序
        Sort sort = Sort.by(Sort.Direction.DESC,"awardTime");
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<Awards> page = awardRepository.findAll(pageable);
        return page;
    }

    /**
     * 3.修改一个奖项
     * @param award 奖项对象
     * @return  若成功返回1
     */
    @Override
    public int updateAward(Awards award) {
        return awardRepository.updateAwardById(award.getAwardId(),award.getAwardName(),award.getAwardTime());
    }

    /**
     * 4.添加一个奖项
     * @param award 奖项对象
     * @return 奖项对象
     */

    @Override
    public Awards addAward(Awards award) {
        return awardRepository.save(award);
    }

    /**
     * 5.通过id删除一个奖项
     * @param awardId 奖项id
     */
    @Override
    public void delAward(Integer awardId) {
        awardRepository.deleteById(awardId);
    }

}
