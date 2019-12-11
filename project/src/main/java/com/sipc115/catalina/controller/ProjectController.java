package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.dataobject.Projects;
import com.sipc115.catalina.service.ProjectService;
import com.sipc115.catalina.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping("/addProject")
    public List<ResultVO> updateProject(MultipartFile image, Integer id , String description, Date time, HttpServletRequest request) throws IOException {

        List<ResultVO> resultVOList = new ArrayList<>();

        if(image!=null && description!=null && !description.trim().isEmpty() && time!=null){
            //用以接收原图与压缩图链接
            List<String> list;

            //获取当前请求全路径 http://localhost:8082/addProject
            //String requestURL =  request.getRequestURL().toString();
            //获取服务器地址 http://localhost:8082/
            //String localhostURL = requestURL.substring(0,requestURL.lastIndexOf(request.getRequestURI()) + 1);


            list = uploadFileService.uploadProjectImage(image);

            //原图相对链接
            String rawImageURL = list.get(0);
            String compressImageURL = list.get(1);

            //封装对象
            Projects project = new Projects();
            project.setProjectId(id);
            project.setProjectDescription(description);
            project.setProjectTime(time);
            project.setProjectImageRaw(rawImageURL);
            project.setProjectImageCompress(compressImageURL);

            projectService.updateProject(project);

            resultVOList.add(new ResultVO(0,"上传成功"));
        }

        //检测项目描述是否为空
        if(description == null || description.trim().isEmpty()){
            resultVOList.add(new ResultVO(1,"项目描述不能为空"));
        }

        //检测时间是否为空
        if(time == null){
            resultVOList.add(new ResultVO(2,"项目时间不能为空"));
        }

        //检测是否上传了图片
        if(image == null){
            resultVOList.add(new ResultVO(3,"项目图片不能为空"));
        }

        //检测图片大小是否超过10MB
        if(image.getSize() > Math.pow(10,7)){
            resultVOList.add(new ResultVO(4,"图片大小超过限制"));
        }

        return resultVOList;
    }

}
