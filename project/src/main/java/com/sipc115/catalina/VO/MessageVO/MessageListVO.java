package com.sipc115.catalina.VO.MessageVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 留言板视图
 */
@Data
public class MessageListVO {

    @JsonProperty("messages_list")
    private List<MessageListInfoVO> messageListInfoVOList;
    @JsonProperty("total")
    private Integer total_message;

}
