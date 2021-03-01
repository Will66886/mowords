package com.midnight.annotation.support;

import com.midnight.annotation.LoginUser;
import com.midnight.service.UserTokenManager;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 统一获取当前用户
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public static final String LOGIN_TOKEN_KEY = "X-MoWords-Token";

    @Override
    //判断返回类型是否是我们要的类型
    public boolean supportsParameter(MethodParameter parameter) {
        //parameter.getParameterType().isAssignableFrom(Integer.class);判断参数及参数的父类是否为Integer类型
        //parameter.hasParameterAnnotation(LoginUser.class);判断参数是否使用LoginUser注解
        return parameter.getParameterType().isAssignableFrom(Integer.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    //解析参数转换成我们需要的类型
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (StringUtils.isEmpty(token)){
            return null;
        }
        return UserTokenManager.getUserId(token);
    }
}
