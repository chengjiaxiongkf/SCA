package com.yc.gateway.handler.filter;

import com.yc.common.util.SignUtil;
import com.yc.gateway.config.GatewayRefreshProperties;
import com.yc.gateway.util.FilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import java.util.*;

/**
 * @Author: ChengJiaXiong
 * @Description:  验签
 * @Date: Created in 12:11 2021/2/19
 */
@Component
@Slf4j
public class SignHandler extends AbstractNoAccessFilterHandler {
    @Autowired
    private GatewayRefreshProperties globalRefreshProperties;

    @Override
    public FilterTypeEnum type() {
        return FilterTypeEnum.SIGN;
    }

    @Override
    public boolean check(ServerWebExchange exchange) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String url = exchange.getRequest().getPath().toString();
        if(!FilterUtils.needSignOrAuth(globalRefreshProperties,url)){
            return true;
        }
        Map<String,String> map = new HashMap<>();
        Map<String,List<String>> linkedHashMap = serverHttpRequest.getQueryParams();
        linkedHashMap.forEach((key,value)-> map.put(key, StringUtils.join(value,","))); //同一个key多个value以逗号分割
        return SignUtil.verifySign(map, globalRefreshProperties.getSignAuthConfigSecret());//验签
    }
}
