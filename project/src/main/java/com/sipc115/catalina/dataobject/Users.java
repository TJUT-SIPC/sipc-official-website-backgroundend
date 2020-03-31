package com.sipc115.catalina.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class Users {

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**用户名*/
    @NotNull(message = "用户名不能为空")
    private String userName;

    /**用户密码*/
    @NotNull(message = "用户密码不能为空")
    private String userPassword;

    /**学号*/
    private String userStudentId;
    /**年龄*/
    private Integer userAge;
    /**性别*/
    private String userGender;
    /**手机号*/
    private String userPhone;
    /**邮箱*/
    private String userEmail;
    /**用户创建时间*/
    private Date userCreateTime;
    /**用户上次登录时间*/
    private Date userLastLogin;
    /**用户权限 0普通用户 1管理员 2超级管理员*/

    @NotNull(message = "用户权限不能为空")
    private Integer userStatus;
    /**备注*/
    private String userRemark;
    /**用户头像URL*/
    private String userHeadImage;
}
