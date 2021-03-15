package com.yc.user.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用户服务动态配置刷新
 * @Author chengjiaxiong
 * @Date 2021/2/7 11:04
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "refresh")
public class UserRefreshProperties {
    private String test;
}
