package com.sipc115.catalina.VO.HomePageVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjectImageVO {

    @JsonProperty("compress")
    private String projectImageCompress;

    @JsonProperty("raw")
    private String projectImageRaw;
}
