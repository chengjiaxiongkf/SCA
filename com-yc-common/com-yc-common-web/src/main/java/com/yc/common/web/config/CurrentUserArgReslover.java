package com.yc.common.web.config;

import com.yc.common.web.annotation.CurrentUser;
import com.yc.common.web.data.User;
import com.yc.common.web.data.UserThreadLocal;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;

/**
 * 参数注入器
 * @CurrentUser注解实现类
 * @author: gongxp
 */
@Component
public class CurrentUserArgReslover implements HandlerMethodArgumentResolver {

    /**
     * 判断注解@CurrentUser是否可以支持
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果该参数注解有@CurrentUser且参数类型是User
        return parameter.getParameterAnnotation(CurrentUser.class) != null &&parameter.getParameterType() == User.class;
    }

    /**
     * 注入参数值
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取得HttpServletRequest
        HttpServletRequest request= (HttpServletRequest) webRequest.getNativeRequest();
        //取出session中的User
        return UserThreadLocal.get();
    }
}
