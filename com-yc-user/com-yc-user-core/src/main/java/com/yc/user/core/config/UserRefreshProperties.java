package com.yc.user.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
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
