package com.sipc115.catalina.VO.AwardVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AwardListVO {

    @JsonProperty("awards_list")
    private List<AwardListInfoVO> awardListInfoVOList;
    @JsonProperty("total")
    private Integer totalAward;
}
