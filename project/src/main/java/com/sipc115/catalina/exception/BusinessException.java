package com.sipc115.catalina.exception;

import com.sipc115.catalina.enums.ResultEnum;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private Integer code;

    public BusinessException(ResultEnum resultEnum){

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();

    }

    public BusinessException(Integer code, String message){

        super(message);

        this.code = code;
    }

}
