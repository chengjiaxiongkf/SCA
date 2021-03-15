package com.yc.common.web.handler.filter;

import lombok.Getter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public abstract boolean check(HttpServletRequest request) throws IOException;

    public enum FilterTypeEnum {
        IP,
        SOURCE,
        SIGN,
        ;
    }
}
