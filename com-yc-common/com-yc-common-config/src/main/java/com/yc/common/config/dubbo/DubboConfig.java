package com.yc.common.config.dubbo;

import org.apache.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 9:51 2021/2/23
 */
@Configuration
public class DubboConfig {
    /**
     * 消费者配置不主动监督zookeeper服务
     *
     * @return
     */
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(20000);
        return consumerConfig;
    }
}
