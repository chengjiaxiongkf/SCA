package com.yc.common.web.handler.filter;

import com.yc.common.web.config.WebConfigProperties;
import com.yc.common.web.util.FilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChengJiaXiong
 * @Description: 黑名单IP过滤处理
 * @Date: Created in 10:32 2021/2/18
 */
@Component
@Slf4j
public class IpHandler extends AbstractNoAccessFilterHandler {
    @Autowired
    private WebConfigProperties webConfigProperties;

    @Override
    public FilterTypeEnum type() {
        return FilterTypeEnum.IP;
    }

    @Override
    public boolean check(HttpServletRequest request) {
        String ip = FilterUtils.getIp(request);
        if(FilterUtils.isBlackListIp(webConfigProperties,ip)){
            log.info("request ip is blackList:"+ip);
            return false;
        }
        return true;
    }
}
