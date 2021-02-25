package com.yc.gateway.factory;

import com.yc.gateway.handler.filter.AbstractNoAccessFilterHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: ChengJiaXiong
 * @Description: 统一拒绝访问过滤的处理过程
 * @Date: Created in 11:43 2021/2/18
 */
@Slf4j
public class NoAccessFilterHandlerFactory {
    private AbstractNoAccessFilterHandler[] filterHandlers;
    public NoAccessFilterHandlerFactory(AbstractNoAccessFilterHandler[] filterHandlers){
        this.filterHandlers = filterHandlers;
    }
    public static NoAccessFilterHandlerFactory build(AbstractNoAccessFilterHandler... filterHandlers){
        return new NoAccessFilterHandlerFactory(filterHandlers);
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 按顺序处理过滤实例
     * @Date: Created in 13:46 2021/2/18
     */
    public boolean process(ServerWebExchange exchange){
        log.info("=========>request url:"+exchange.getRequest().getURI().getPath());
        log.info("=========>request url:"+exchange.getRequest().getMethod());
        for (AbstractNoAccessFilterHandler filterHandler:filterHandlers) {
            if(!process(exchange,filterHandler)){
                return false;
            }
        }
        return true;
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 返回true通过，返回false不通过
     * @Date: Created in 11:49 2021/2/18
     */
    private boolean process(ServerWebExchange exchange, AbstractNoAccessFilterHandler filterHandler){
        log.info("filter type:"+filterHandler.getFilterTypeEnum().toString());
        if(!filterHandler.check(exchange)){
            log.info("process fail");
            return false;
        }
        return true;
    }
}
