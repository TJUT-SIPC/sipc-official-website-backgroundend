package com.sipc115.catalina.VO.HomePageVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HomeAwardListInfoVO {

    /**获奖编号*/
    @JsonProperty("id")
    private Integer awardId;

    /**获奖名称*/
    @JsonProperty("name")
    private String awardName;

    /**获奖时间*/
    @JsonProperty("time")
    private String awardTime;
}
