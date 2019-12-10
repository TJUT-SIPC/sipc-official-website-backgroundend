package com.sipc115.catalina.VO.HomePageVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 项目集合视图
 */
@Data
public class ProjectAndAwardListVO {

    @JsonProperty("project_list")
    private List<ProjectListInfoVO> projectListInfoVOList;

    @JsonProperty("award_list")
    private List<AwardListInfoVO> awardListInfoVOList;

}
