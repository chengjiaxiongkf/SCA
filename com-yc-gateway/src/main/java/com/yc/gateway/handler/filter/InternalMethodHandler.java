package com.yc.gateway.handler.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: ChengJiaXiong
 * @Description:  内部API方法只允许RPC调用，禁止外部调用    //TODO 暂未明确是否需要
 * @Date: Created in 11:06 2021/2/19
 */
@Component
public class InternalMethodHandler extends AbstractNoAccessFilterHandler {
    @Override
    public FilterTypeEnum type() {
        return FilterTypeEnum.INTERNAL_METHOD;
    }

    @Override
    public boolean check(ServerWebExchange exchange) {
        return true;
    }
}
