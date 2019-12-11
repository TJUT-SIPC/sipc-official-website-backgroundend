package com.sipc115.catalina.VO.UserVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserListVO {

    @JsonProperty("users_list")
    private List<UserListInfoVO> userListInfoVOList;

    @JsonProperty("total")
    private Integer total_users;
}
