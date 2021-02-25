package com.yc.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态配置刷新
 * @Author chengjiaxiong
 * @Date 2021/2/7 11:04
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "refresh")
public class GatewayRefreshProperties {
    private List<String> sources = new ArrayList<>();   //请求来源
    private List<String> blackListIp = new ArrayList<>(); //黑名单ip
    private List<String> noAuthListUrl = new ArrayList<>();//不需要授权的请求
    private String signAuthConfigSecret;    //验签密钥
}
