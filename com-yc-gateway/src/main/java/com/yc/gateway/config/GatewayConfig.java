package com.yc.gateway.config;

import com.yc.gateway.filter.user.UserFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ChengJiaXiong
 * @Description: 自定义路由注入
 * @Date: Created in 14:13 2021/2/10
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator getUserFilter(RouteLocatorBuilder builder) {
        return builder.routes().
                route(predicateSpec -> predicateSpec
                        .path("/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.stripPrefix(1).filter(new UserFilter()))
                        .uri("lb://com-yc-user")
                        .id("user-filter")).build();
    }
}
