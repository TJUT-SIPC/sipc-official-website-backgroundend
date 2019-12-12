package com.sipc115.catalina.VO.UserVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserListInfoVO {

    @JsonProperty("id")
    private Integer userId;
    @JsonProperty("name")
    private String userName;
    @JsonProperty("password")
    private String userPassword;
    @JsonProperty("student_id")
    private String userStudentId;
    @JsonProperty("age")
    private Integer userAge;
    @JsonProperty("gender")
    private String userGender;
    @JsonProperty("phone")
    private String userPhone;
    @JsonProperty("email")
    private String userEmail;
    @JsonProperty("create_time")
    private String userCreateTime;
    @JsonProperty("last_login")
    private String userLastLogin;
    @JsonProperty("status")
    private Integer userStatus;
    @JsonProperty("remark")
    private String userRemark;
    @JsonProperty("head_image")
    private String userHeadImage;

}
