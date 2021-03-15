package com.yc.common.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.redis.util.RedisUtils;
import com.yc.common.web.data.User;
import com.yc.common.web.data.UserThreadLocal;
import com.yc.common.util.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ChengJiaXiong
 * @Description: 拦截器封装用户基本信息
 * @Date: Created in 12:48 2021/2/24
 */
@Component
@Slf4j
public class InterceptorUserInfo implements HandlerInterceptor {
    @Resource
    private RedisUtils redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(SecurityConstant.JWT_TOKEN);
        if(StringUtils.isEmpty(token) || "1".equals(token)){
            return true;
        }
        Object obj = redisUtil.get(token);
        if(obj==null){
            return true;
        }
        String tokenValue = obj.toString();
        User user = JSONObject.parseObject(tokenValue, User.class);
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //返回
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        UserThreadLocal.remove();   //销毁本地线程数据
    }
}
