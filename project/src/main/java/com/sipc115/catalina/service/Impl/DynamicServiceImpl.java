package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Dynamics;
import com.sipc115.catalina.repository.DynamicRepository;
import com.sipc115.catalina.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicServiceImpl implements DynamicService {

    @Autowired
    private DynamicRepository dynamicRepository;

    /**
     * 1.通过id查询一个动态
     * @param dynamicId 动态id
     * @return 动态对象
     */
    @Override
    public Dynamics findOne(Integer dynamicId) {
        return dynamicRepository.findById(dynamicId).get();
    }

    /***
     * 2.分页查询所有动态
     * @param pageNum 页数
     * @param pageSize  一页显示多少条
     * @return 查询到的动态集合
     */
    @Override
    public List<Dynamics> findAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Dynamics> page = dynamicRepository.findAll(pageable);
        return page.getContent();
    }

    /**
     * 3.修改一个动态
     * @param dynamic 动态对象
     * @return  若成功返回1
     */
    @Override
    public int updateDynamic(Dynamics dynamic) {
        return dynamicRepository.updateDynamicById(dynamic.getDynamicId(),dynamic.getDynamicImage(),dynamic.getDynamicHeader(),
                dynamic.getDynamicText(),dynamic.getDynamicEditor(),dynamic.getDynamicCategory());
    }

    /**
     * 4.添加一个动态
     * @param dynamic 动态对象
     * @return 动态对象
     */
    @Override
    public Dynamics addDynamic(Dynamics dynamic) {
        return dynamicRepository.save(dynamic);
    }

    @Override
    public void delDynamic(Integer dynamicId) {
        dynamicRepository.deleteById(dynamicId);
    }
}
