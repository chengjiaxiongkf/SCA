package com.yc.common.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:  web文本配置
 * @Date: Created in 18:38 2021/3/12
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "web.config")
public class WebConfigProperties {
    private int enabled = 1;                                     //自动注入默认1启动 0不启动
    private List<String> blackListIp = new ArrayList<>();        //黑名单ip
    private List<String> ignoredTokenSource = new ArrayList<>(); //不需要鉴权的请求来源
    private List<String> ignoredToken = new ArrayList<>();       //跳过token鉴权的请求
    private List<String> ignoredSign = new ArrayList<>();        //跳过sign验签的请求
    private String signAuthConfigSecret;                         //验签密钥
}
