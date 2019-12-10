package com.sipc115.catalina.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */
@Data
public class ResultVO<T> {
    //返回代码
    private Integer code;
    //提示信息
    private String msg;
    //返回具体内容
    private T data;
}
