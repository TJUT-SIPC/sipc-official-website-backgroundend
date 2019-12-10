package com.sipc115.catalina.VO.WishVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WishListVO {

    @JsonProperty("wishes_list")
    private List<WishListInfoVO> wishListInfoVOList;

}
