package com.sipc115.catalina.service;

import com.sipc115.catalina.dataobject.Users;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户
 */
@Service
public interface UserService {

    /**单个用户查询*/
    Users findeOne(Integer userId);

    /**分页查询所有用户*/
    List<Users> findAll(Integer pageNum, Integer pageSize);

    /**修改用户*/
    int updateUser(Users user);

    /**添加用户*/
    Users addUser(Users user);

    /**删除用户*/
    void delUser(Integer userId);

}
