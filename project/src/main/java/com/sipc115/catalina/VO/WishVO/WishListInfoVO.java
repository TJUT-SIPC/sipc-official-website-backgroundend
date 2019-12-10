package com.sipc115.catalina.VO.WishVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WishListInfoVO {

    @JsonProperty("id")
    private Integer wishId;

    @JsonProperty("name")
    private String wishName;

    @JsonProperty("word")
    private String wishWord;

}
