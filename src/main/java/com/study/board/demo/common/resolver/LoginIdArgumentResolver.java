package com.study.board.demo.common.resolver;

import com.study.board.demo.common.annotation.LoginId;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;
import java.util.Objects;

@Component
public class LoginIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return supportAnnotation(LoginId.class, parameter);
    }

    private <T extends Annotation> boolean supportAnnotation(Class<T> annotationClz, MethodParameter parameter){
        T annotaion = parameter.getParameterAnnotation(annotationClz);
        if(Objects.isNull(annotaion)) return false;
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return getLoginId(webRequest);
    }

    public String getLoginId(NativeWebRequest webRequest){
        return (String)webRequest.getAttribute("loginId", RequestAttributes.SCOPE_SESSION);
    }
}
