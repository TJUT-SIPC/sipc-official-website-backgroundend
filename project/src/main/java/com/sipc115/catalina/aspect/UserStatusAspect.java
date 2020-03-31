package com.sipc115.catalina.aspect;

import com.sipc115.catalina.enums.ResultEnum;
import com.sipc115.catalina.exception.BusinessException;
import com.sipc115.catalina.service.CheckUserStatusService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserStatusAspect {

    @Autowired
    private CheckUserStatusService checkUserStatusService;

    /**切入点*/
    @Pointcut("execution(* com.sipc115.catalina.controller.*.*_ADMIN(..))")
    public void adminCheckAspect(){

    }
    @Pointcut("execution(* com.sipc115.catalina.controller.*.*_ROOT(..))")
    public void rootCheckAspect(){

    }

    /**切面*/
    @Before(value = "adminCheckAspect()")
    public void adminCheck() throws Exception {
        if(checkUserStatusService.isAdmin()){
            System.out.println("----------管理员身份检验成功----------");
        }else {
            throw new BusinessException(ResultEnum.PERMISSION_DENIED);
        }
    }

    @Before(value = "rootCheckAspect()")
    public void rootCheck() throws Exception {
        if(checkUserStatusService.isRoot()){
            System.out.println("----------超级管理员身份检验成功----------");
        }else {
            throw new BusinessException(ResultEnum.PERMISSION_DENIED);
        }
    }

}
