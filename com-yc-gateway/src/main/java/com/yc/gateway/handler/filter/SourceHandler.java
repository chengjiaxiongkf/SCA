package com.yc.gateway.handler.filter;

import com.yc.common.util.SecurityConstant;
import com.yc.gateway.config.GatewayRefreshProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: ChengJiaXiong
 * @Description: 来源过滤处理
 * @Date: Created in 11:25 2021/2/18
 */
@Component
public class SourceHandler extends AbstractNoAccessFilterHandler {
    public SourceHandler(){
        super();
    }

    @Override
    public FilterTypeEnum type() {
        return FilterTypeEnum.SOURCE;
    }

    @Autowired
    private GatewayRefreshProperties globalRefreshProperties;

    @Override
    public boolean check(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String source = headers.getFirst(SecurityConstant.SOURCE);//请求来源
        return globalRefreshProperties.getSources().indexOf(source)!=-1;
    }
}
