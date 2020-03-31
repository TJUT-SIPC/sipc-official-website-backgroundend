package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ProjectVO.CenterProjectListInfoVO;
import com.sipc115.catalina.VO.ProjectVO.ProjectImageVO;
import com.sipc115.catalina.VO.ProjectVO.ProjectListVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.annotation.LoginRequired;
import com.sipc115.catalina.dataobject.Projects;
import com.sipc115.catalina.enums.ResultEnum;
import com.sipc115.catalina.exception.BusinessException;
import com.sipc115.catalina.service.ProjectService;
import com.sipc115.catalina.service.UploadFileService;
import com.sipc115.catalina.utils.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/projectCenter")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UploadFileService uploadFileService;

    //图片链接集合[原图,压缩图]
    private List<String> projectImageList;

    /**
     * 1.修改一条项目
     * @param id                项目id
     * @param description       项目描述
     * @param time              项目时间
     * @param rawImageURL       原图URL
     * @param compressImageURL  压缩图URL
     * @param request           http请求
     * @return                  ResultVO
     * @throws IOException
     */
    @PostMapping("/modifyProject")
    @LoginRequired
    public ResultVO updateProject_ADMIN(Integer id , String description, Date time, String rawImageURL, String compressImageURL, HttpServletRequest request) throws IOException {

        if(id == null){
            log.error("传入项目id为空,project_id={}",id);
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }

        if(rawImageURL!=null && compressImageURL!=null && description!=null && !description.trim().isEmpty() && time!=null){

            //1.若图像更新，删除原有图像资源
            Projects project = projectService.findOne(id);
            if(!rawImageURL.equals(project.getProjectImageRaw()) && !compressImageURL.equals(project.getProjectImageCompress())){
                uploadFileService.deleteImage(URLUtil.getVirtualLocalhostPath() + project.getProjectImageRaw());
                uploadFileService.deleteImage(URLUtil.getVirtualLocalhostPath() + project.getProjectImageCompress());
            }

            //2.封装对象
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
        return new ResultVO(3,"项目图片不能为空");
    }

    /**
     * 2.查询所有项目
     * @param page 当前页数
     * @param pageSize 一页显示多少条
     * @return
     */
    @PostMapping("/getProjects")
    @LoginRequired
    public ResultVO getProjects_ADMIN(Integer page, Integer pageSize, HttpServletRequest request){

        System.out.println("pageSize:" + pageSize);

        //限制一页可查询5~100条
        if(page == null || pageSize == null){
            log.error("page或pageSize缺失,page={}, pageSize={}",page,pageSize);
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }
        if(pageSize < 5)  pageSize = 5;
        if(pageSize > 100) pageSize = 100;

        //日期格式化 yyyy/M
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M");

        //1.分页查询所有项目
        Page<Projects> projectListPage = projectService.findAll(page - 1,pageSize);

        List<Projects> projectList = projectListPage.getContent();

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

            if(project.getProjectTime()!=null)
            centerProjectListInfoVO.setProjectTime(sdf.format(project.getProjectTime()));

            centerProjectListInfoVO.setImg(projectImageVO);

            centerProjectListInfoVOList.add(centerProjectListInfoVO);
        }
        projectListVO.setCenterProjectListInfoVOList(centerProjectListInfoVOList);
        projectListVO.setTotal_project((int) projectListPage.getTotalElements());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(projectListVO);

        return resultVO;
    }

    /**
     * 3.添加一个项目
     * @param description 项目描述
     * @param time  项目时间
     * @param rawImageURL 项目原图URL
     * @param compressImageURL 项目压缩图URL
     * @return ResultVO
     * @throws IOException
     */
    @PostMapping("/addProject")
    @LoginRequired
    public ResultVO addProject_ADMIN(String description, Date time, String rawImageURL, String compressImageURL) throws IOException {

        //1.验证参数
        if(description!=null && !description.trim().isEmpty() && time!=null && rawImageURL!=null && compressImageURL!=null){

            //2.封装对象
            Projects project = new Projects();
            project.setProjectDescription(description);
            project.setProjectTime(time);
            project.setProjectImageRaw(rawImageURL);
            project.setProjectImageCompress(compressImageURL);

            projectService.addProject(project);

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
        return new ResultVO(3,"项目图片不能为空");

    }

    /**
     * 4.删除一个项目
     * @param id 要删除的项目id
     * @return ResultVO
     */
    @PostMapping("/delProject")
    @LoginRequired
    public ResultVO delProject_ADMIN(Integer id){

        if(id == null){
            log.error("传入项目id为空,project_id={}",id);
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }

        //1.删除项目相关图片资源
        Projects project = projectService.findOne(id);
        uploadFileService.deleteImage(URLUtil.getVirtualLocalhostPath() + project.getProjectImageRaw());
        uploadFileService.deleteImage(URLUtil.getVirtualLocalhostPath() + project.getProjectImageCompress());

        //2.删除数据库中该条项目
        projectService.delProject(id);
        return new ResultVO(0,"success");
    }

}
