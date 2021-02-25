package com.yc.mall.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:07 2021/2/24
 */
@SpringBootApplication(scanBasePackages = {"com.yc.mall.core","com.yc.common.config"})
public class AppMain {
    public static void main(String[] args) {
        new SpringApplication(AppMain.class).run(args);
    }
}
