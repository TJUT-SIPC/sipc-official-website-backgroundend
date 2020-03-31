package com.sipc115.catalina.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数缺失或不正确"),
    USER_ID_NOT_EXIST(2,"通过id查找用户不存在"),
    USER_NAME_NOT_EXIST(3,"通过用户名查找用户不存在"),
    PROJECT_ID_NOT_EXIST(4,"通过id查找项目不存在"),
    DYNAMIC_ID_NOT_EXIST(5,"通过id查找动态不存在"),
    PERMISSION_DENIED(404,"拒绝请求,用户权限不足");
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

}
