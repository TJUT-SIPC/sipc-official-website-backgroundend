package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.VO.UserVO.UserListInfoVO;
import com.sipc115.catalina.VO.UserVO.UserListVO;
import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.service.UserService;
import com.sipc115.catalina.utils.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getUsers")
    public ResultVO getUsers(@RequestParam("page")Integer page, @RequestParam("pageSize")Integer pageSize , HttpServletRequest request){

        //日期格式化 yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //1.分页查询所有用户
        List<Users> userList = userService.findAll(page - 1,pageSize);

        //2.数据组装
        UserListVO userListVO = new UserListVO();

        List<UserListInfoVO> userListInfoVOList = new ArrayList();

        for(Users user : userList){
            //传入用户信息
            UserListInfoVO userListInfoVO = new UserListInfoVO();
            userListInfoVO.setUserId(user.getUserId());
            userListInfoVO.setUserName(user.getUserName());
            userListInfoVO.setUserPassword(user.getUserPassword());
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

}
