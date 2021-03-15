package com.yc.common.web;

import com.yc.common.web.config.CurrentUserArgReslover;
import com.yc.common.web.config.WebConfigProperties;
import com.yc.common.web.config.WebMvcConfiguration;
import com.yc.common.web.filter.GlobalFilter;
import com.yc.common.web.filter.InterceptorUserInfo;
import com.yc.common.web.handler.filter.IpHandler;
import com.yc.common.web.handler.filter.SignHandler;
import com.yc.common.web.handler.filter.SourceHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 18:33 2021/3/12
 */
@Configuration
@ConditionalOnProperty(name = "web.config.enabled")//配置文件存在web.config.enabled才启动，必须存在该配置
public class WebConfigAutoConfiguration implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                CurrentUserArgReslover.class.getName(),
                WebConfigProperties.class.getName(),
                WebMvcConfiguration.class.getName(),
                GlobalFilter.class.getName(),
                InterceptorUserInfo.class.getName(),
                IpHandler.class.getName(),
                SignHandler.class.getName(),
                SourceHandler.class.getName()
        };
    }
}
