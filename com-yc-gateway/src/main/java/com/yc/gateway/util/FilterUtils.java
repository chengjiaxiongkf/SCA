package com.yc.gateway.util;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.base.dto.Result;
import com.yc.gateway.config.GatewayRefreshProperties;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 13:48 2021/2/19
 */
public class FilterUtils {
    /**
     * @Author: ChengJiaXiong
     * @Description: 获取IP
     * @Date: Created in 13:48 2021/2/19
     */
    public static String getIp(ServerHttpRequest request){
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(request.getRemoteAddress()).getAddress().getHostAddress();
        }
        return ip;
    }


    /**
     * @Author: ChengJiaXiong
     * @Description: 返回输出
     * @Date: Created in 11:55 2021/2/19
     */
    public static void out(ServerHttpResponse response, Result result){
        //TODO 好像flux的原因，response这么改写不生效
        byte[] bytes = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
        response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * @Author: ChengJiaXiong
     * @Description:  重定向跳转
     * @Date: Created in 15:44 2021/2/19
     */
    public static void redirt(ServerHttpResponse response,String url){
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set(com.google.common.net.HttpHeaders.LOCATION,url);
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 是否需要验签
     * @Date: Created in 18:29 2021/2/19
     */
    public static boolean needSignOrAuth(GatewayRefreshProperties gatewayRefreshProperties, String url){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return gatewayRefreshProperties.getNoAuthListUrl().stream().anyMatch(s -> antPathMatcher.match(s,url));
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 是否IP黑名单
     * @Date: Created in 18:29 2021/2/19
     */
    public static boolean isBlackListIp(GatewayRefreshProperties gatewayRefreshProperties, String ip){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return gatewayRefreshProperties.getBlackListIp().stream().anyMatch(s -> antPathMatcher.match(s,ip));
    }
}
