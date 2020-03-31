package com.sipc115.catalina.handler;

import com.sipc115.catalina.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理 BusinessException异常
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Object businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        log.info("业务异常。code:" + e.getCode() + "msg:" + e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("code",e.getCode());
        map.put("msg", e.getMessage());
        map.put("url", request.getRequestURL().toString());

        return map;
    }
}