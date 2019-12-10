package com.sipc115.catalina.VO.DynamicVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DynamicListInfoVO {

    @JsonProperty("id")
    private Integer dynamicId;

    @JsonProperty("image")
    private String dynamicImage;

    @JsonProperty("header")
    private String dynamicHeader;

    @JsonProperty("text")
    private String dynamicText;

    @JsonProperty("time")
    private String dynamicTime;

    @JsonProperty("editor")
    private String dynamicEditor;

    @JsonProperty("category")
    private Integer dynamicCategory;
}
