package com.yc.common.web.util;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.base.dto.Result;
import com.yc.common.base.enums.AppTypeEnum;
import com.yc.common.util.HttpUtils;
import com.yc.common.web.config.WebConfigProperties;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * 过滤处理工具类
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
    public static String getIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip != null && ip.length() != 0) {
            ip = ip.split(",")[0];  //多个取第一个
        }
        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
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
     * @Description: 是否需要鉴权
     * @Date: Created in 18:29 2021/2/19
     * @Retun: true不需要鉴权  false需要鉴权
     */
    public static boolean isAuthentication(WebConfigProperties webConfigProperties, String url,HttpServletRequest request){
        AppTypeEnum appTypeEnum = HttpUtils.getAppType(request);
        if(webConfigProperties.getIgnoredTokenSource().indexOf(appTypeEnum.toString()) != -1){ //跳过鉴权的来源返回true
            return true;
        }
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return webConfigProperties.getIgnoredToken().stream().anyMatch(s -> antPathMatcher.match(s,url));
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 是否需要sig验签
     * @Date: Created in 18:29 2021/2/19
     * @Retun: true不需要鉴权  false需要鉴权
     */
    public static boolean isSign(WebConfigProperties webConfigProperties, String url){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return webConfigProperties.getIgnoredSign().stream().anyMatch(s -> antPathMatcher.match(s,url));
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 是否IP黑名单
     * @Date: Created in 18:29 2021/2/19
     */
    public static boolean isBlackListIp(WebConfigProperties webConfigProperties, String ip){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return webConfigProperties.getBlackListIp().stream().anyMatch(s -> antPathMatcher.match(s,ip));
    }
}
