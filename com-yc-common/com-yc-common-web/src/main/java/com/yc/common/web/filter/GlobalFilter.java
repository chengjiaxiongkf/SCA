package com.yc.common.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.yc.auth.api.dto.AuthDTO;
import com.yc.auth.api.dto.AuthResultDTO;
import com.yc.auth.api.facade.AuthFacade;
import com.yc.common.base.enums.ResultCodeEnum;
import com.yc.common.base.util.ResultUtil;
import com.yc.common.util.HttpUtils;
import com.yc.common.util.SecurityConstant;
import com.yc.common.web.config.WebConfigProperties;
import com.yc.common.web.factory.NoAccessFilterHandlerFactory;
import com.yc.common.web.handler.filter.IpHandler;
import com.yc.common.web.handler.filter.SignHandler;
import com.yc.common.web.handler.filter.SourceHandler;
import com.yc.common.web.util.FilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: ChengJiaXiong
 * @Description:  全局过滤配置
 * @Date: Created in 14:17 2021/3/8
 */
@Component
@Slf4j
public class GlobalFilter implements Filter {
    @Autowired
    private IpHandler ipHandler;
    @Autowired
    private SourceHandler sourceHandler;
    @Autowired
    private SignHandler signHandler;
    @Autowired
    private WebConfigProperties webConfigProperties;
    @DubboReference
    private AuthFacade authFacade;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(webConfigProperties.getEnabled() == 0){  //用于开发环境跳过token跟sign校验
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        log.info("GlobalFilter checking...");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String url = request.getServletPath();
        String token = request.getHeader(SecurityConstant.JWT_TOKEN);
        String menuId = request.getHeader(SecurityConstant.MENU_ID);
        String method = request.getMethod();
        //option直接下一步
        if (HttpMethod.OPTIONS.toString().equals(Objects.requireNonNull(request.getMethod()))) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(!NoAccessFilterHandlerFactory.build(ipHandler,sourceHandler,signHandler).process(request)){ //基本过滤验证
            HttpUtils.out(response, ResultUtil.failed(ResultCodeEnum.NO_ACCESS));
            return;
        }
        //url跟请求来源是否需要鉴权
        if(FilterUtils.isAuthentication(webConfigProperties,url,request)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(menuId)) { //请求头token或menuId为空
            HttpUtils.out(response, ResultUtil.failed(ResultCodeEnum.NO_ACCESS));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //sso授权
        AuthDTO authenticationDTO = new AuthDTO();
        authenticationDTO.setUrl(url);
        authenticationDTO.setToken(token);
        authenticationDTO.setMenuId(Long.parseLong(menuId));
        authenticationDTO.setMethod(method);
        AuthResultDTO authentication = authFacade.authentication(authenticationDTO);
        if(!"200".equals(authentication.getCode())){
            log.info("authentication fail:"+ JSONObject.toJSONString(authentication));
            HttpUtils.out(response, ResultUtil.failed(ResultCodeEnum.FORBIDDEN));
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
