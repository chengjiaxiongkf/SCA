package com.yc.web.uke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 17:47 2021/3/4
 */
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
        new SpringApplication(AppMain.class).run(args);
    }
}
