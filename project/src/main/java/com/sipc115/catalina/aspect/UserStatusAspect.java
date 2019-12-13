package com.sipc115.catalina.aspect;

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

    @Pointcut("execution(* com.sipc115.catalina.controller.AwardController.*(..))")
    public void awardAspect(){

    }
    @Pointcut("execution(* com.sipc115.catalina.controller.DynamicController.*(..))")
    public void dynamicAspect(){

    }
    @Pointcut("execution(* com.sipc115.catalina.controller.MessageController.*(..))")
    public void messageAspect(){

    }
    @Pointcut("execution(* com.sipc115.catalina.controller.ProjectController.*(..))")
    public void projectAspect(){

    }
    @Pointcut("execution(* com.sipc115.catalina.controller.WishController.*(..))")
    public void wishAspect(){

    }
    @Pointcut("execution(* com.sipc115.catalina.controller.UploadFileController.*(..))")
    public void uploadFileAspect(){

    }
    @Pointcut("execution(* com.sipc115.catalina.controller.UserController.*(..))")
    public void userAspect(){

    }

    @Before(value = "awardAspect()")
    public void awardBefore() throws Exception {
        if(checkUserStatusService.isAdmin()){
            System.out.println("管理员身份检验成功");
        }else {
            throw new Exception("权限不足");
        }
    }

    @Before(value = "dynamicAspect()")
    public void dynamicBefore() throws Exception {
        if(checkUserStatusService.isAdmin()){
            System.out.println("管理员身份检验成功");
        }else {
            throw new Exception("权限不足");
        }
    }
    @Before(value = "messageAspect()")
    public void messageBefore() throws Exception {
        if(checkUserStatusService.isAdmin()){
            System.out.println("管理员身份检验成功");
        }else {
            throw new Exception("权限不足");
        }
    }
    @Before(value = "projectAspect()")
    public void projectBefore() throws Exception {
        if(checkUserStatusService.isAdmin()){
            System.out.println("管理员身份检验成功");
        }else {
            throw new Exception("权限不足");
        }
    }
    @Before(value = "wishAspect()")
    public void wishBefore() throws Exception {
        if(checkUserStatusService.isAdmin()){
            System.out.println("管理员身份检验成功");
        }else {
            throw new Exception("权限不足");
        }
    }
    @Before(value = "uploadFileAspect()")
    public void uploadFileBefore() throws Exception {
        if(checkUserStatusService.isAdmin()){
            System.out.println("管理员身份检验成功");
        }else {
            throw new Exception("权限不足");
        }
    }
    @Before(value = "userAspect()")
    public void userBefore() throws Exception {
        if(checkUserStatusService.isRoot()){
            System.out.println("管理员身份检验成功");
        }else {
            throw new Exception("权限不足");
        }
    }

}
