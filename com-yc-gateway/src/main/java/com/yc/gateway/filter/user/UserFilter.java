package com.yc.gateway.filter.user;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户模块自定义过滤器
 * @Date: Created in 14:19 2021/2/10
 */
public class UserFilter implements GatewayFilter, Ordered {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }
}
