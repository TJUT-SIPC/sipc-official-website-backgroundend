package com.sipc115.catalina.VO.HomePageVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 项目与奖项集合视图
 */
@Data
public class ProjectAndAwardListVO {

    @JsonProperty("project_list")
    private List<HomeProjectListInfoVO> homeProjectListInfoVOList;

    @JsonProperty("award_list")
    private List<HomeAwardListInfoVO> homeAwardListInfoVOList;

}
