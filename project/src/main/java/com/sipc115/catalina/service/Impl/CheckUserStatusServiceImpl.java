package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.enums.UserStatusEnum;
import com.sipc115.catalina.service.CheckUserStatusService;
import com.sipc115.catalina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class CheckUserStatusServiceImpl implements CheckUserStatusService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 1.二级管理员检验（管理员、超级管理员身份）
     * @return 若符合则返回true，否则返回false
     */
    @Override
    public boolean isAdmin() {
        HttpSession session = request.getSession();
        Users user = userService.findOne((Integer) session.getAttribute("userId"));
        if(user.getUserStatus() >= UserStatusEnum.ADMIN.getCode()){
            return true;
        }
        return false;
    }

    /**
     * 2.一级管理员检验（超级管理员身份）
     * @return 若符合则返回true，否则返回false
     */
    @Override
    public boolean isRoot() {
        HttpSession session = request.getSession();
        Users user = userService.findOne((Integer) session.getAttribute("userId"));
        if(user.getUserStatus() == UserStatusEnum.ROOT.getCode()){
            return true;
        }
        return false;
    }
}
