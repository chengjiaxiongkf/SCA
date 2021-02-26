package com.yc.gateway.config;

import com.alibaba.csp.sentinel.adapter.spring.webflux.callback.BlockRequestHandler;
import com.yc.common.base.dto.ResultCodeEnum;
import com.yc.common.base.dto.ResultUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 16:12 2021/2/22
 */
@Component
public class GatewayFallbackConfig implements BlockRequestHandler {
    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
        return ServerResponse.status(200).syncBody(ResultUtil.failed(ResultCodeEnum.NO_ACCESS));
    }
}
