package com.yc.common.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.base.data.User;
import com.yc.common.base.data.UserThreadLocal;
import com.yc.common.config.redis.util.RedisUtil;
import com.yc.common.util.SecurityConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 12:48 2021/2/24
 */
@Component
public class UserInfoFilter implements Filter {
    @Resource
    private RedisUtil redisUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String token = request.getHeader(SecurityConstant.JWT_TOKEN);
        if(StringUtils.isEmpty(token)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        Object obj = redisUtil.get(token);
        if(obj==null){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String tokenValue = obj.toString();
        User user = JSONObject.parseObject(tokenValue, User.class);
        UserThreadLocal.set(user);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
