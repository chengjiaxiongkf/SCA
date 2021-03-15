package com.yc.common.web.handler.filter;

import com.yc.common.base.enums.AppTypeEnum;
import com.yc.common.util.AssertUtils;
import com.yc.common.util.HttpUtils;
import com.yc.common.web.config.WebConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChengJiaXiong
 * @Description: 来源过滤处理
 * @Date: Created in 11:25 2021/2/18
 */
@Component
@Slf4j
public class SourceHandler extends AbstractNoAccessFilterHandler {
    public SourceHandler(){
        super();
    }

    @Override
    public FilterTypeEnum type() {
        return FilterTypeEnum.SOURCE;
    }

    @Autowired
    private WebConfigProperties webConfigProperties;

    /**
     * @Author: ChengJiaXiong
     * @Description: 校验请求来源是否合法
     * @Date: Created in 14:38 2021/3/8
     * @Params:  false非法来源 true通过
     */
    @Override
    public boolean check(HttpServletRequest request) {
        AppTypeEnum appTypeEnum = HttpUtils.getAppType(request);
        if(appTypeEnum==null){
            log.info("request source is valid.");
            return false;
        }
        return true;
    }
}
