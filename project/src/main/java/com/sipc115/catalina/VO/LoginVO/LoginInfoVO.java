package com.sipc115.catalina.VO.LoginVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginInfoVO {

    @JsonProperty("id")
    private Integer userId;

    @JsonProperty("name")
    private String username;

    @JsonProperty("token")
    private String token;

}
