package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.DynamicVO.DynamicListInfoVO;
import com.sipc115.catalina.VO.DynamicVO.DynamicListVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.dataobject.Dynamics;
import com.sipc115.catalina.service.DynamicService;
import com.sipc115.catalina.service.UploadFileService;
import com.sipc115.catalina.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class DynamicController {

    @Autowired
    private DynamicService dynamicService;
    @Autowired
    private UploadFileService uploadFileService;

    //动态图片集合[原图,压缩图]
    private List<String> dynamicHeadImageList;

    /**
     * 1.网站首页返回动态
     * @param page
     * @return
     */
    @GetMapping("/dynamics")
    public ResultVO getDynamics(Integer page, HttpServletRequest request){

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
            dynamicListInfoVO.setDynamicImage(URLUtil.getLocalhostURL(request) + dynamic.getDynamicImage());
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


    /**
     * 2.分页返回所有动态
     * @param page      页数
     * @param pageSize  一页返回多少条
     * @return
     */
    @GetMapping("/dynamicCenter/getDynamics")
    public ResultVO resultVO(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request){

        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if(pageSize<5) pageSize=5;
        if(pageSize>100) pageSize=100;

        //1.分页查询所有动态
        List<Dynamics> dynamicList = dynamicService.findAll(page, pageSize);

        //2.数据组装
        DynamicListVO dynamicListVO = new DynamicListVO();

        List<DynamicListInfoVO> dynamicListInfoVOList = new ArrayList();

        for(Dynamics dynamic : dynamicList){

            DynamicListInfoVO dynamicListInfoVO = new DynamicListInfoVO();

            //传入动态id，image，header，text，time，editor，category
            dynamicListInfoVO.setDynamicId(dynamic.getDynamicId());
            dynamicListInfoVO.setDynamicImage(URLUtil.getLocalhostURL(request) + dynamic.getDynamicImage());
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


    /**
     * 3.添加一条动态 (是否必须)
     * @param image     动态图片(N)
     * @param header    动态标题(Y)
     * @param text      动态内容(Y)
     * @param editor    编辑者(N)
     * @param category  分类编号(N)
     * @return ResultVO
     * @throws IOException
     */
    @PostMapping("/dynamicCenter/addDynamic")
    public ResultVO addDynamic(MultipartFile image, String header, String text, String editor, Integer category) throws IOException {

        //1.验证必须参数
        boolean rightHeader = (header != null) && (header.length()<=50) && !header.trim().isEmpty();
        boolean rightText = (text != null) && !text.trim().isEmpty();

        //2.保存数据
        if(rightHeader && rightText){

            String imageURL = null;
            if(image!=null){
                //接收动态图片链接
                dynamicHeadImageList = uploadFileService.uploadDynamicImage(image);
                //相对链接 0 原图 ，1 压缩图
                imageURL = dynamicHeadImageList.get(0);
            }

            //封装对象
            Dynamics dynamic = new Dynamics();
            dynamic.setDynamicImage(imageURL);
            dynamic.setDynamicHeader(header);
            dynamic.setDynamicText(text);
            dynamic.setDynamicEditor(editor);
            dynamic.setDynamicCategory(category);

            dynamicService.addDynamic(dynamic);

            return new ResultVO(0,"success");
        }
        if(!rightHeader){
            return new ResultVO(1,"动态标题不能为空");
        }
        if(!rightText){
            return new ResultVO(2,"动态内容不能为空");
        }
        return null;
    }

    /**
     * 4.通过id修改动态
     * @param id        要修改的动态id
     * @param image     图片
     * @param header    标题
     * @param text      内容
     * @param editor    编辑者
     * @param category  分类编号
     * @return
     * @throws IOException
     */
    @PostMapping("/dynamicCenter/modifyDynamic")
    public ResultVO modifyDynamic(Integer id, MultipartFile image, String header, String text, String editor, Integer category) throws IOException {

        //1.验证必须参数
        boolean rightHeader = (header != null) && (header.length()<=50);
        boolean rightText = (text != null);

        //2.保存数据
        if(rightHeader && rightText ){

            String imageURL = null;
            if(image!=null){
                //接收动态图片链接
                dynamicHeadImageList = uploadFileService.uploadDynamicImage(image);
                //相对链接 0 原图 ，1 压缩图
                imageURL = dynamicHeadImageList.get(0);
            }

            //封装对象
            Dynamics dynamic = new Dynamics();
            dynamic.setDynamicId(id);
            dynamic.setDynamicImage(imageURL);
            dynamic.setDynamicHeader(header);
            dynamic.setDynamicText(text);
            dynamic.setDynamicEditor(editor);
            dynamic.setDynamicCategory(category);

            dynamicService.updateDynamic(dynamic);

            return new ResultVO(0,"success");
        }
        if(!rightHeader){
            return new ResultVO(1,"动态标题不能为空");
        }
        if(!rightText){
            return new ResultVO(2,"动态内容不能为空");
        }
        return null;
    }


    /**
     * 5.通过id删除一条动态
     * @param id   动态id
     * @return
     */
    @PostMapping("/dynamicCenter/delDynamic")
    public ResultVO delDynamic(Integer id){

        dynamicService.delDynamic(id);
        return new ResultVO(0,"success");
    }

}
