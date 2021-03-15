package com.yc.common.web.handler.filter;

import com.yc.common.util.HttpUtils;
import com.yc.common.util.SignUtil;
import com.yc.common.web.config.WebConfigProperties;
import com.yc.common.web.data.BodyReaderHttpServletRequestWrapper;
import com.yc.common.web.util.FilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.SortedMap;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:50 2021/3/15
 */
@Component
@Slf4j
public class SignHandler extends AbstractNoAccessFilterHandler {
    @Autowired
    private WebConfigProperties webConfigProperties;

    @Override
    public FilterTypeEnum type() {
        return FilterTypeEnum.SIGN;
    }

    @Override
    public boolean check(HttpServletRequest request) throws IOException {
        String url = request.getServletPath();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if(FilterUtils.isSign(webConfigProperties,url)){
            return true;
        }
        HttpServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        SortedMap<String, Object> allParams = HttpUtils.getAllParams(requestWrapper);
        return  SignUtil.verifySign(allParams, webConfigProperties.getSignAuthConfigSecret());
    }
}
