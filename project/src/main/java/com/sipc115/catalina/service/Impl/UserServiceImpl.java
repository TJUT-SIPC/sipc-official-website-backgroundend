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

    /**
     * 1.通过id查询一个用户
     * @param userId 用户id
     * @return 用户对象
     */
    @Override
    public Users findeOne(Integer userId) {
        return userRepository.findOne(userId);
    }

    @Override
    /**
     * 2.分页查询所有用户
     * @param pageNum 页数
     * @param pageSize 一页显示多少条
     * @return 查询到的用户集合
     */
    public List<Users> findAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum,pageSize);
        Page<Users> page = userRepository.findAll(pageable);
        return page.getContent();
    }

    /**
     * 3.分页查询不同权限用户
     * @param userStatus   用户权限代码
     * @param pageNum   页数
     * @param pageSize  一页显示多少条
     * @return
     */
    @Override
    public List<Users> findAllByUserStatus(Integer userStatus, Integer pageNum, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Users> page = userRepository.findAllByStatus(userStatus, pageable);
        return page.getContent();
    }

    /**
     * 4.更新用户信息
     * @param user 用户对象
     * @return 若成功返回1
     */
    @Override
    public int updateUser(Users user) {
        int msg = userRepository.updateUserById(user.getUserId(),user.getUserName(),user.getUserPassword(),
                user.getUserStudentId() ,user.getUserAge(),user.getUserGender(),user.getUserPhone(),
                user.getUserEmail(),user.getUserLastLogin(),user.getUserStatus(),
                user.getUserRemark(),user.getUserHeadImage());
        return msg;
    }

    /**
     * 5.添加新用户
     * @param user 用户对象
     * @return  用户对象
     */
    @Override
    public Users addUser(Users user){
        return userRepository.save(user);
    }

    /**
     * 6.通过id删除一个用户
     * @param userId 用户id
     */
    @Override
    public void delUser(Integer userId){
        userRepository.delete(userId);
    }


}
