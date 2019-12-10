package com.sipc115.catalina.enums;

import lombok.Getter;

/**
 * 用户权限标识
 */
@Getter
public enum UserStatusEnum {

    NORMAL(0,"普通用户"),
    ADMIN(1,"管理员"),
    ROOT(2,"超级管理员");


    private Integer code;

    private String msg;

    UserStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }


}
