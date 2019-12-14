package com.sipc115.catalina.resolver;

import com.sipc115.catalina.annotation.CurrentUser;
import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.configuration.UserConstants;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 用于判定是否需要处理该参数分解，返回true为需要，并会调用下面resolveArgument
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        System.out.println("----------supportsParameter----------"+methodParameter.getParameterType());
        //判断是否能转成Users类型
        return methodParameter.getParameterType().isAssignableFrom(Users.class)
                //是否有CurrentUser注解
                && methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    /**
     * 真正用于处理参数分解的方法，返回的object对象为controller方法上的形参对象
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        System.out.println("----------resolveArgument----------"+methodParameter);
        Users user = (Users) nativeWebRequest.getAttribute(UserConstants.CURRENT_USER, RequestAttributes.SCOPE_REQUEST);
        if(user != null){
            return user;
        }
        throw new MissingServletRequestPartException(UserConstants.CURRENT_USER);
    }
}
