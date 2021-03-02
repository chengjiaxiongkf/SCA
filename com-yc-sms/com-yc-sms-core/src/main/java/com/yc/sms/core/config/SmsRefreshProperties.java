package com.yc.sms.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:00 2021/2/22
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "refresh")
public class SmsRefreshProperties {
    //对应accessKeyId
    private String accessKeyId;
    //对应accessKeySercret
    private String accessSecret;
    //对应accessKeySercret
    private String regionId;
    //短信API产品域名
    private String domain;
    //短信API产品域名
    private String product;
    //短信API签名
    private String signName;
}
