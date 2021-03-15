package com.yc.common.web.factory;

import com.yc.common.web.handler.filter.AbstractNoAccessFilterHandler;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public boolean process(HttpServletRequest request) throws IOException {
        log.info("=========>request url:"+request.getServletPath());
        log.info("=========>request url:"+request.getMethod());
        for (AbstractNoAccessFilterHandler filterHandler:filterHandlers) {
            if(!process(request,filterHandler)){
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
    private boolean process(HttpServletRequest request, AbstractNoAccessFilterHandler filterHandler) throws IOException {
        log.info("filter type:"+filterHandler.getFilterTypeEnum().toString());
        if(!filterHandler.check(request)){
            log.info("process fail");
            return false;
        }
        return true;
    }
}
