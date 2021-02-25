package com.yc.gateway.handler.filter;

import com.yc.gateway.config.GatewayRefreshProperties;
import com.yc.gateway.util.FilterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: ChengJiaXiong
 * @Description: 黑名单IP过滤处理
 * @Date: Created in 10:32 2021/2/18
 */
@Component
public class IpHandler extends AbstractNoAccessFilterHandler {
    @Autowired
    private GatewayRefreshProperties gatewayRefreshProperties;

    @Override
    public FilterTypeEnum type() {
        return FilterTypeEnum.IP;
    }

    @Override
    public boolean check(ServerWebExchange exchange) {
        String ip = FilterUtils.getIp(exchange.getRequest());
        return !FilterUtils.isBlackListIp(gatewayRefreshProperties,ip);
    }
}
