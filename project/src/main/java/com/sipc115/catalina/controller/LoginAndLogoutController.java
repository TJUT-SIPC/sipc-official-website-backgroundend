package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.LoginVO.LoginInfoVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.configuration.UserConstants;
import com.sipc115.catalina.service.LoginService;
import com.sipc115.catalina.service.LogoutService;
import com.sipc115.catalina.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
@Slf4j
public class LoginAndLogoutController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private LogoutService logoutService;

    /**
     * 1.登录功能
     * @param username  用户名
     * @param password  密码
     * @return          ResultVO
     */
    @PostMapping("/login")
    public ResultVO userLogin(String username, String password){

        ResultVO resultVO = new ResultVO();

        //验证用户名与密码
        Users user = userService.findOneByUserName(username);

        if(user == null){
            log.error("[登录失败]用户不存在,username={},password={}",username,password);
            resultVO.setCode(1001);
            resultVO.setMsg("用户不存在");
            return resultVO;
        }else {
            //检验密码
            if(password.equals(user.getUserPassword())){

                //数据组装，返回用户id，用户名，token
                LoginInfoVO loginInfoVO = new LoginInfoVO();
                loginInfoVO.setUserId(user.getUserId());


                loginInfoVO.setUsername(user.getUserName());
                loginInfoVO.setToken(loginService.login(user));

                resultVO.setCode(1000);
                resultVO.setMsg("success");
                resultVO.setData(loginInfoVO);

                return resultVO;

            } else {
                log.error("[登录失败]密码错误 username={} ,password={}",username,password);
                resultVO.setCode(1002);
                resultVO.setMsg("密码错误");

                return resultVO;
            }
        }
    }


    /**
     * 2.注销功能
     * @return ResultVO
     */
    @PostMapping("/logout")
    public ResultVO logout(HttpServletRequest request){
        String token = UserConstants.REDIS_USER + request.getHeader(UserConstants.ACCESS_TOKEN_HEAD);
        logoutService.logout(token);
        return new ResultVO(0,"success");
    }

}
