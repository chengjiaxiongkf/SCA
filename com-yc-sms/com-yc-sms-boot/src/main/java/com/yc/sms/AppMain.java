package com.yc.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:37 2021/3/2
 */
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
        new SpringApplication(AppMain.class).run(args);
    }
}
