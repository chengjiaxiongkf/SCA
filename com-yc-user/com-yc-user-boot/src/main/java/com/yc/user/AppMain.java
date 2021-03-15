package com.yc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 17:44 2021/3/3
 */
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplication(AppMain.class).run(args);
    }
}
