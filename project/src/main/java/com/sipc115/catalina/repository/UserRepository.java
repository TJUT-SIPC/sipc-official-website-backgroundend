package com.sipc115.catalina.repository;

import com.sipc115.catalina.dataobject.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    /**通过id查询一个用户*/
    @Override
    Users findOne(Integer integer);


    @Override
    /**分页查询所有用户*/
    Page<Users> findAll(Pageable pageable);


    /**通过id修改一个用户*/
    @Modifying
    @Transactional
    @Query("update Users set userName=:userName, userPassword=:userPassword," +
            "userAge=:userAge, userGender=:userGender, userPhone=:userPhone, userEmail=:userEmail," +
            "userLastLogin=:userLastLogin, userStatus=:userStatus, userRemark=:userRemark," +
            "userHeadImage=:userHeadImage where userId = :userId")
    int updateUserById(@Param("userId") Integer userIda, @Param("userName")String userName,
                       @Param("userPassword")String userPassword, @Param("userAge")Integer userAge,
                       @Param("userGender")String userGender, @Param("userPhone")String userPhone,
                       @Param("userEmail")String userEmail, @Param("userLastLogin")Date userLastLogin,
                       @Param("userStatus")Integer userStatus, @Param("userRemark")String userRemark,
                       @Param("userHeadImage") String userHeadImage);


    /**加入一个用户*/
    @Override
    Users save(Users user);

    /**通过id删除一个用户*/
    @Override
    void delete(Integer integer);
}
