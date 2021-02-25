package com.yc.user.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author chengjiaxiong
 * @Date 2021/2/7 10:29
 */
@SpringBootApplication(scanBasePackages = {"com.yc.user.core", "com.yc.common.config"})
public class AppMain {
    public static void main(String[] args) {
        new SpringApplication(AppMain.class).run(args);
    }
}
