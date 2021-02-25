package com.yc.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.yc.authentication.api.dto.AuthenticationDTO;
import com.yc.authentication.api.service.AuthenticationService;
import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultCodeEnum;
import com.yc.common.base.util.ResultUtil;
import com.yc.common.config.redis.util.RedisUtil;
import com.yc.gateway.config.GatewayRefreshProperties;
import com.yc.gateway.factory.NoAccessFilterHandlerFactory;
import com.yc.gateway.handler.filter.IpHandler;
import com.yc.gateway.handler.filter.SignHandler;
import com.yc.gateway.handler.filter.SourceHandler;
import com.yc.gateway.util.FilterUtils;
import com.yc.common.util.SecurityConstant;
import com.yc.user.api.service.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Author: ChengJiaXiong
 * @Description: 全局基本过滤
 * @Date: Created in 10:00 2021/2/10
 */
@Component
@Slf4j
@Order(value = 0)
public class GlobalBasicFilter implements GlobalFilter {
    @Autowired
    private IpHandler ipHandler;

    @Autowired
    private SourceHandler sourceHandler;

    @Autowired
    private SignHandler signHandler;

    @Autowired
    private GatewayRefreshProperties gatewayRefreshProperties;

    @Resource
    private RedisUtil redisUtil;

    @DubboReference(group = "interfaceAuthentication")
    private AuthenticationService authenticationService;

    @DubboReference
    private NodeService nodeService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("GlobalBasicFilter checking...");
        log.info("request info: " + JSON.toJSONString(exchange.getRequest()));
        log.info("response info: " + JSON.toJSONString(exchange.getResponse()));
        log.info("header info: " + JSON.toJSONString(exchange.getRequest().getHeaders()));
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getPath().toString();
        String token = request.getHeaders().getFirst(SecurityConstant.JWT_TOKEN);
        String menuId = request.getHeaders().getFirst(SecurityConstant.MENU_ID);
        String method = request.getMethodValue();
        //option直接下一步
        if (HttpMethod.OPTIONS.toString().equals(Objects.requireNonNull(request.getMethod()).toString())) {
            return chain.filter(exchange);
        }
        if(!NoAccessFilterHandlerFactory.build(ipHandler,sourceHandler,signHandler).process(exchange)){ //基本过滤验证失败
            FilterUtils.out(response,ResultUtil.failed(ResultCodeEnum.NO_ACCESS));
            response.setStatusCode(HttpStatus.BAD_REQUEST);  //错误的请求
            return exchange.getResponse().setComplete();
        }
        if(this.checkAuthentication(method,url)){ //url是否需要授权
            return chain.filter(exchange);
        }
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(menuId)){ //请求头token或menuId为空
            log.info("token:"+token+" menuId:"+menuId);
            FilterUtils.out(response,ResultUtil.failed(ResultCodeEnum.UNAUTHORIZED));
            response.setStatusCode(HttpStatus.UNAUTHORIZED);  //未登录或已过期
            return exchange.getResponse().setComplete();
        }
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setUrl(url);
        authenticationDTO.setToken(token);
        authenticationDTO.setMenuId(Long.parseLong(menuId));
        authenticationDTO.setMethod(method);
        Result result = authenticationService.authentication(authenticationDTO);
        if(!ResultUtil.isSuccess(result)){
            log.info("authentication fail");
            FilterUtils.out(response,ResultUtil.failed(ResultCodeEnum.UNAUTHORIZED));
            response.setStatusCode(HttpStatus.UNAUTHORIZED);  //未登录或已过期
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: url是否需要授权的
     * @Retun: true不需要授权 false需要授权
     * @Date: Created in 14:32 2021/2/9
     */
    private boolean checkAuthentication(String method,String url){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if(FilterUtils.needSignOrAuth(gatewayRefreshProperties,url)){
            return true;
        }
        //TODO redis缓存 RedisKeyContant.NODE_ALL_LIST
        List<String> nodes = nodeService.getAllNode();
        String uri = method+"|"+url;
        return nodes.stream().anyMatch(s -> antPathMatcher.match(s,uri));
    }
}
