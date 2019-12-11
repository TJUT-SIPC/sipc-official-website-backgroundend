package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ProjectVO.CenterProjectListInfoVO;
import com.sipc115.catalina.VO.ProjectVO.ProjectImageVO;
import com.sipc115.catalina.VO.ProjectVO.ProjectListVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.dataobject.Projects;
import com.sipc115.catalina.service.ProjectService;
import com.sipc115.catalina.service.UploadFileService;
import com.sipc115.catalina.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/projectCenter")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 1.修改一条项目
     * @param id    项目id
     * @param description   项目描述
     * @param time  项目时间
     * @param image 项目图片
     * @param request   http请求
     * @return ResultVO
     * @throws IOException
     */
    @PostMapping("/modifyProject")
    public ResultVO updateProject(Integer id , String description, Date time, MultipartFile image, HttpServletRequest request) throws IOException {

        if(image!=null && description!=null && !description.trim().isEmpty() && time!=null){

            //用以接收原图与压缩图链接
            List<String> list;

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

            return new ResultVO(0,"success");
        }

        //检测项目描述是否为空
        if(description == null || description.trim().isEmpty()){
            return new ResultVO(1,"项目描述不能为空");
        }

        //检测时间是否为空
        if(time == null){
            return new ResultVO(2,"项目时间不能为空");
        }

        //检测是否上传了图片
        if(image == null){
            return new ResultVO(3,"项目图片不能为空");
        }

        //检测图片大小是否超过10MB
        if(image.getSize() > Math.pow(10,7)){
            return new ResultVO(4,"图片大小超过限制");
        }

        return new ResultVO(0,"success");

    }

    /**
     * 2.查询所有项目
     * @param page 当前页数
     * @param pageSize 一页显示多少条
     * @return
     */
    @PostMapping("/getProjects")
    public ResultVO getProjects(Integer page, Integer pageSize, HttpServletRequest request){

        System.out.println("pageSize:" + pageSize);

        //限制一页可查询5~100条
        if(pageSize < 5)  pageSize = 5;
        if(pageSize > 100) pageSize = 100;

        //日期格式化 yyyy/M
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M");

        //1.分页查询所有项目
        List<Projects> projectList = projectService.findAll(page - 1,pageSize);

        //2.数据组装
        ProjectListVO projectListVO = new ProjectListVO();

        List<CenterProjectListInfoVO> centerProjectListInfoVOList = new ArrayList();

        for(Projects project : projectList){
            //传入图像链接
            ProjectImageVO projectImageVO = new ProjectImageVO();
            projectImageVO.setProjectImageCompress(URLUtil.getLocalhostURL(request) + project.getProjectImageCompress());
            projectImageVO.setProjectImageRaw(URLUtil.getLocalhostURL(request) + project.getProjectImageRaw());

            //传入项目id，描述，图像对象
            CenterProjectListInfoVO centerProjectListInfoVO = new CenterProjectListInfoVO();
            centerProjectListInfoVO.setProjectId(project.getProjectId());
            centerProjectListInfoVO.setProjectDescription(project.getProjectDescription());
            centerProjectListInfoVO.setProjectTime(sdf.format(project.getProjectTime()));
            centerProjectListInfoVO.setImg(projectImageVO);

            centerProjectListInfoVOList.add(centerProjectListInfoVO);
        }
        projectListVO.setCenterProjectListInfoVOList(centerProjectListInfoVOList);
        projectListVO.setTotal_project(centerProjectListInfoVOList.size());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(projectListVO);

        return resultVO;
    }


}
