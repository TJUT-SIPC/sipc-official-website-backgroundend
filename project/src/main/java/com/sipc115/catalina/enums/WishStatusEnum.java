package com.sipc115.catalina.enums;

import lombok.Getter;

@Getter
public enum WishStatusEnum {

    FAILED(0,"审核不通过"),
    DEFAULT(1,"待审核"),
    CHECKED(2,"审核通过待发布"),
    PUBLISH(3,"已发布");

    private Integer code;

    private String msg;

    WishStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
