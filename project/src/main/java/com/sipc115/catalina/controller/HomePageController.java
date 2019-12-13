package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.AwardVO.AwardListInfoVO;
import com.sipc115.catalina.VO.DynamicVO.DynamicListInfoVO;
import com.sipc115.catalina.VO.DynamicVO.DynamicListVO;
import com.sipc115.catalina.VO.ProjectVO.ProjectImageVO;
import com.sipc115.catalina.VO.HomePageVO.HomeProjectListInfoVO;
import com.sipc115.catalina.VO.HomePageVO.ProjectAndAwardListVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.VO.WishVO.WishListInfoVO;
import com.sipc115.catalina.VO.WishVO.WishListVO;
import com.sipc115.catalina.dataobject.*;
import com.sipc115.catalina.service.*;
import com.sipc115.catalina.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private DynamicService dynamicService;
    @Autowired
    private MessageBoardService messageBoardService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private WishService wishService;

    /**
     * 1.网站首页获取项目与奖项
     * @param request
     * @return
     */
    @GetMapping("/")
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

    /**
     * 2.网站首页返回动态
     * @param page
     * @return
     */

    @GetMapping("/dynamics")
    public ResultVO getDynamicsInLimit(Integer page, HttpServletRequest request){

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
     * 3.首页上传留言功能
     * @param email     联系邮箱
     * @param nickname  昵称
     * @param advice    建议
     * @return
     */
    @PostMapping("/sendMessage")
    public ResultVO sendMessage(String email, String nickname, String advice){

        /**验证参数*/
        boolean rightEmail = false;
        String emailRegex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$";

        if(email.matches(emailRegex)){
            rightEmail = true;
        }

        if(rightEmail && nickname!=null && !nickname.trim().isEmpty() && advice!=null && !advice.trim().isEmpty() ) {
            /**保存建议*/
            MessageBoard message = new MessageBoard();
            message.setBoardEmail(email);
            message.setBoardNickname(nickname);
            message.setBoardAdvice(advice);
            messageBoardService.addMessage(message);

            return new ResultVO(0,"success");
        }
        if(!rightEmail){
            return new ResultVO(1,"邮箱格式错误");
        }
        if(nickname==null || nickname.trim().isEmpty()){
            return new ResultVO(2,"名字不能为空");
        }
        if(advice==null || advice.trim().isEmpty()){
            return new ResultVO(3,"建议不能为空");
        }

        return new ResultVO(0,"提交成功");
    }

    /**
     * 4.首页随机返回15条寄语
     * @return ResultVO
     */
    @GetMapping("/wishes")
    public ResultVO getWishesInHomePage(){

        //1.随机查询15条已发布寄语
        List<Wishes> wishList = wishService.findWishesByLimit(15);

        //2.数据组装

        WishListVO wishListVO = new WishListVO();

        List<WishListInfoVO> wishListInfoVOList = new ArrayList();

        for(Wishes wish : wishList){

            WishListInfoVO wishListInfoVO = new WishListInfoVO();

            //传入id，name，word
            wishListInfoVO.setWishId(wish.getWishId());
            wishListInfoVO.setWishName(wish.getWishName());
            wishListInfoVO.setWishWord(wish.getWishWord());

            wishListInfoVOList.add(wishListInfoVO);
        }

        wishListVO.setWishListInfoVOList(wishListInfoVOList);
        wishListVO.setTotal_wishes(wishListInfoVOList.size());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(wishListVO);

        return resultVO;

    }

    /**
     * 5.首页上传一条寄语
     * @param name 写寄语的人名
     * @param word 寄语内容
     * @return ResultVO
     */
    @PostMapping("/sendWishes")
    public ResultVO sendWish(String name, String word){

        /**验证参数*/
        if(name!=null && !name.trim().isEmpty() && word!=null && !word.trim().isEmpty()){
            /**保存寄语*/
            Wishes wish = new Wishes();
            wish.setWishName(name);
            wish.setWishWord(word);
            wishService.addWish(wish);

            return new ResultVO(0,"success");
        }
        if(name == null || name.trim().isEmpty()){
            return new ResultVO(1,"人名不能为空");
        }
        if(word == null || word.trim().isEmpty()){
            return new ResultVO(2,"寄语不能为空");
        }
        return new ResultVO(0,"success");
    }
}
