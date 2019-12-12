package com.sipc115.catalina.VO.ImageVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageVO {

    @JsonProperty("image_raw")
    private String rawImageURL;
    @JsonProperty("image_compress")
    private String compressImageURL;

}
