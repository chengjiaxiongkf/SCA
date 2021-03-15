package com.yc.web.sso.config;

import lombok.Getter;
import lombok.Setter;
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
public class SsoRefreshProperties {
    private String tokenAuthConfigSecret;
    private List<Integer> platforms = new ArrayList<Integer>();//需要权限校验的platform
}
