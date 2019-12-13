package com.sipc115.catalina.service;

import org.springframework.stereotype.Service;

@Service
public interface CheckUserStatusService {

    /**二级以上权限检验（管理员、超级管理员身份检验）*/
    boolean isAdmin();

    /**一级权限检验（超级管理员身份检验）*/
    boolean isRoot();

}
