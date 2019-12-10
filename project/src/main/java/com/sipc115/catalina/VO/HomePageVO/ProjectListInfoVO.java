package com.sipc115.catalina.VO.HomePageVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjectListInfoVO<T> {

    //返回项目id
    @JsonProperty("id")
    private Integer projectId;

    //返回描述
    @JsonProperty("description")
    private String projectDescription;

    //返回图片
    private T img;

}
