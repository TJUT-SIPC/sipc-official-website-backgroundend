package com.sipc115.catalina.VO.MessageVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageListInfoVO {

    @JsonProperty("id")
    private Integer messageId;
    @JsonProperty("email")
    private String messageEmail;
    @JsonProperty("nickname")
    private String messageNickname;
    @JsonProperty("advice")
    private String messageAdvice;
    @JsonProperty("upload_time")
    private String messageUploadTime;
}
