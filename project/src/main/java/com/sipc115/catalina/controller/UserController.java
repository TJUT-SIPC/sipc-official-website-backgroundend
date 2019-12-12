package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.VO.UserVO.UserListInfoVO;
import com.sipc115.catalina.VO.UserVO.UserListVO;
import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.enums.UserStatusEnum;
import com.sipc115.catalina.service.UserAndAwardService;
import com.sipc115.catalina.service.UserService;
import com.sipc115.catalina.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userCenter")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAndAwardService userAndAwardService;

    /**
     * 1.分页获取所有用户
     * @param page 当前查询页数
     * @param pageSize  一页显示多少条
     * @param status 查询特定类用户[0普通用户，1管理员，2超级管理员，3全部查询]
     * @param request
     * @return
     */
    @GetMapping("/getUsers")
    public ResultVO getUsers(@RequestParam("page")Integer page, @RequestParam("pageSize")Integer pageSize ,@RequestParam("status") Integer status, HttpServletRequest request){

        //日期格式化 yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(pageSize<5) pageSize=5;
        if(pageSize>100) pageSize=100;

        //1.分页查询所有用户
        List<Users> userList;
        if(status >= UserStatusEnum.NORMAL.getCode() && status <=UserStatusEnum.ROOT.getCode()){
            userList = userService.findAllByUserStatus(status, page - 1, pageSize);
        }else{
            userList = userService.findAll(page - 1, pageSize);
        }

        //2.数据组装
        UserListVO userListVO = new UserListVO();

        List<UserListInfoVO> userListInfoVOList = new ArrayList();

        for(Users user : userList){
            //传入用户信息
            UserListInfoVO userListInfoVO = new UserListInfoVO();
            userListInfoVO.setUserId(user.getUserId());
            userListInfoVO.setUserName(user.getUserName());
            userListInfoVO.setUserPassword(user.getUserPassword());
            userListInfoVO.setUserStudentId(user.getUserStudentId());
            userListInfoVO.setUserAge(user.getUserAge());
            userListInfoVO.setUserGender(user.getUserGender());
            userListInfoVO.setUserPhone(user.getUserPhone());
            userListInfoVO.setUserEmail(user.getUserEmail());
            userListInfoVO.setUserCreateTime(sdf.format(user.getUserCreateTime()));
            userListInfoVO.setUserLastLogin((user.getUserLastLogin()!=null)? sdf.format(user.getUserLastLogin()):null);
            userListInfoVO.setUserStatus(user.getUserStatus());
            userListInfoVO.setUserRemark(user.getUserRemark());
            userListInfoVO.setUserHeadImage(URLUtil.getLocalhostURL(request) + user.getUserHeadImage());

            userListInfoVOList.add(userListInfoVO);
        }

        userListVO.setUserListInfoVOList(userListInfoVOList);
        userListVO.setTotal_users(userListInfoVOList.size());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(userListVO);

        return resultVO;
    }

    @PostMapping("/delUser")
    public ResultVO delUser(Integer id){

        //1.先删除用户关联的奖项记录
        userAndAwardService.delAwardByUserId(id);
        //2.删除一个用户
        userService.delUser(id);

        return new ResultVO(0,"success");

    }

}
