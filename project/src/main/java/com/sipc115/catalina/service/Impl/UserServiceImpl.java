package com.sipc115.catalina.service.Impl;

import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.repository.UserRepository;
import com.sipc115.catalina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users findeOne(Integer userId) {
        return null;
    }

    @Override
    /***
     * pageNum 页数
     * pageSize 每页显示多少条
     */
    public List<Users> findAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum,pageSize);
        Page<Users> page = userRepository.findAll(pageable);
        return page.getContent();
    }

    /**
     * 通过id更新用户信息
     * @param user 用户对象
     * @return 成功返回1
     */
    @Override
    public int updateUserById(Users user) {
        int msg = userRepository.updateUserById(user.getUserId(),user.getUserName(),user.getUserPassword(),
                user.getUserAge(),user.getUserGender(),user.getUserPhone(),
                user.getUserEmail(),user.getUserLastLogin(),user.getUserStatus(),
                user.getUserRemark(),user.getUserHeadImage());
        return msg;
    }

    /**
     * 添加新用户
     * @param user 用户对象
     * @return  用户对象
     */
    public Users addUser(Users user){
        return userRepository.save(user);
    }

    /**
     * 通过id删除一个用户
     * @param userId 用户id
     */
    public void delUser(Integer userId){
        userRepository.delete(userId);
    }


}
