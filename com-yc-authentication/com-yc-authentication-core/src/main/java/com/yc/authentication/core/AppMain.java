package com.yc.authentication.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 17:28 2021/2/22
 */
@SpringBootApplication(scanBasePackages = {"com.yc.authentication.core","com.yc.common.config"})
public class AppMain {
    public static void main(String[] args) {
        new SpringApplication(AppMain.class).run(args);
    }
}
