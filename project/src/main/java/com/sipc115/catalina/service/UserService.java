package com.sipc115.catalina.service;

import com.sipc115.catalina.dataobject.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户
 */
@Service
public interface UserService {

    /**通过id进行单个用户查询*/
    Users findOne(Integer userId);

    /**通过用户名进行单个用户查询*/
    Users findOneByUserName(String username);

    /**分页查询所有用户*/
    List<Users> findAll(Integer pageNum, Integer pageSize);

    /**分页查询不同权限用户*/
    List<Users> findAllByUserStatus(Integer userStatus, Integer pageNum, Integer pageSize);

    /**修改用户*/
    int updateUser(Users user);

    /**添加用户*/
    Users addUser(Users user);

    /**删除用户*/
    void delUser(Integer userId);

}
