package com.yc.sms.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:37 2021/3/2
 */
@SpringBootApplication(scanBasePackages = {"com.yc.sms.core","com.yc.common.config.common","com.yc.common.config.dubbo"})
public class AppMain {
    public static void main(String[] args) {
        new SpringApplication(AppMain.class).run(args);
    }
}
