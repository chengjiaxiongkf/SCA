package com.yc.common.config.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 18:47 2021/2/22
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "global.refresh")
public class GlobalRefreshProperties {
}
