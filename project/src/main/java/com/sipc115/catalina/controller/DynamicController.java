package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.DynamicVO.DynamicListInfoVO;
import com.sipc115.catalina.VO.DynamicVO.DynamicListVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.dataobject.Dynamics;
import com.sipc115.catalina.service.DynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class DynamicController {

    @Autowired
    private DynamicService dynamicService;

    @GetMapping("/dynamics")
    public ResultVO getDynamics(Integer page){

        System.out.println("接收到页数"+page);

        //日期格式化 yyyy/M/d
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");

        //1.分页查询动态，一页5条
        List<Dynamics> dynamicList = dynamicService.findAll(page,5);

        //2.数据组装

        DynamicListVO dynamicListVO = new DynamicListVO();

        List<DynamicListInfoVO> dynamicListInfoVOList = new ArrayList();

        for(Dynamics dynamic : dynamicList){

            DynamicListInfoVO dynamicListInfoVO = new DynamicListInfoVO();

            //传入动态id，image，header，text，time，editor，category
            dynamicListInfoVO.setDynamicId(dynamic.getDynamicId());
            dynamicListInfoVO.setDynamicImage(dynamic.getDynamicImage());
            dynamicListInfoVO.setDynamicHeader(dynamic.getDynamicHeader());
            dynamicListInfoVO.setDynamicText(dynamic.getDynamicText());
            dynamicListInfoVO.setDynamicTime(sdf.format(dynamic.getDynamicTime()));
            dynamicListInfoVO.setDynamicEditor(dynamic.getDynamicEditor());
            dynamicListInfoVO.setDynamicCategory(dynamic.getDynamicCategory());

            dynamicListInfoVOList.add(dynamicListInfoVO);
        }

        dynamicListVO.setDynamicListInfoVOList(dynamicListInfoVOList);
        dynamicListVO.setTotalPage(dynamicListInfoVOList.size());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(dynamicListVO);

        return resultVO;
    }

}
