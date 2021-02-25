package com.yc.gateway.handler.filter;

import lombok.Getter;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: ChengJiaXiong
 * @Description 定义拒绝访问过滤器处理行为
 * @Date: Created in 11:28 2021/2/18
 */
@Getter
public abstract class AbstractNoAccessFilterHandler {

    private FilterTypeEnum filterTypeEnum;

    public AbstractNoAccessFilterHandler(){
        this.filterTypeEnum = type();
    }

    public abstract FilterTypeEnum type();

    /**
     * @Author: ChengJiaXiong
     * @Description: 自定义校验 true通过 false不通过
     * @Date: Created in 11:52 2021/2/18
     */
    public abstract boolean check(ServerWebExchange exchange);

    public enum FilterTypeEnum {
        IP,
        SOURCE,
        INTERNAL_METHOD,
        SIGN,
        ;
    }
}
