package com.sipc115.catalina.VO.ProjectVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CenterProjectListInfoVO<T> {

    //返回项目id
    @JsonProperty("id")
    private Integer projectId;

    //返回描述
    @JsonProperty("description")
    private String projectDescription;

    //返回时间
    @JsonProperty("time")
    private String projectTime;

    //返回图片
    private T img;

}