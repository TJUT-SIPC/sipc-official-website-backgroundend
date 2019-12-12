package com.sipc115.catalina.enums;

import lombok.Getter;

/**
 * 图片质量标识
 */
@Getter
public enum ImageQualityEnum {

    LOW(0,"压缩图"),
    RAW(1,"原图");

    private Integer code;

    private String msg;

    ImageQualityEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
