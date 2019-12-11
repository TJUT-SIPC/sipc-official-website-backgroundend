package com.sipc115.catalina.VO.ProjectVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sipc115.catalina.VO.HomePageVO.HomeProjectListInfoVO;
import lombok.Data;

import java.util.List;

@Data
public class ProjectListVO {

    @JsonProperty("projects_list")
    private List<CenterProjectListInfoVO> centerProjectListInfoVOList;

    @JsonProperty("total")
    private Integer total_project;

}
