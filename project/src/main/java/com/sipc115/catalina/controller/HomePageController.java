package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.AwardVO.AwardListInfoVO;
import com.sipc115.catalina.VO.ProjectVO.ProjectImageVO;
import com.sipc115.catalina.VO.HomePageVO.HomeProjectListInfoVO;
import com.sipc115.catalina.VO.HomePageVO.ProjectAndAwardListVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.annotation.LoginRequired;
import com.sipc115.catalina.dataobject.Awards;
import com.sipc115.catalina.dataobject.Projects;
import com.sipc115.catalina.service.AwardService;
import com.sipc115.catalina.service.ProjectService;
import com.sipc115.catalina.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomePageController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AwardService awardService;

    @GetMapping("/")
    @LoginRequired
    public ResultVO getProjectAndAwardList(HttpServletRequest request){

        //日期格式化 yyyy/M
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M");

        //1.查询所有项目
        List<Projects> projectList = projectService.findAll(0,1000);
        //2.查询所有奖项
        List<Awards> awardList = awardService.findAll(0,1000);

        //3.数据组装
        ProjectAndAwardListVO projectAndAwardListVO = new ProjectAndAwardListVO();

        List<HomeProjectListInfoVO> homeProjectListInfoVOList = new ArrayList();

        List<AwardListInfoVO> awardListInfoVOList = new ArrayList();

        /**project_list部分组装*/
        for(Projects project : projectList){
            //传入图像链接
            ProjectImageVO projectImageVO = new ProjectImageVO();
            projectImageVO.setProjectImageCompress(URLUtil.getLocalhostURL(request) + project.getProjectImageCompress());
            projectImageVO.setProjectImageRaw(URLUtil.getLocalhostURL(request) + project.getProjectImageRaw());

            //传入项目id，描述，图像对象
            HomeProjectListInfoVO homeProjectListInfoVO = new HomeProjectListInfoVO();
            homeProjectListInfoVO.setProjectId(project.getProjectId());
            homeProjectListInfoVO.setProjectDescription(project.getProjectDescription());
            homeProjectListInfoVO.setImg(projectImageVO);

            homeProjectListInfoVOList.add(homeProjectListInfoVO);
        }
        projectAndAwardListVO.setHomeProjectListInfoVOList(homeProjectListInfoVOList);


        /**award_list部分组装*/
        for(Awards award : awardList){
            //传入获奖项目id，名称，日期
            AwardListInfoVO awardListInfoVO = new AwardListInfoVO();
            awardListInfoVO.setAwardId(award.getAwardId());
            awardListInfoVO.setAwardName(award.getAwardName());

            if(award.getAwardTime()!=null){
                awardListInfoVO.setAwardTime(sdf.format(award.getAwardTime()));
            }

            awardListInfoVOList.add(awardListInfoVO);
        }
        projectAndAwardListVO.setAwardListInfoVOList(awardListInfoVOList);

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(projectAndAwardListVO);

        return resultVO;

    }
}
