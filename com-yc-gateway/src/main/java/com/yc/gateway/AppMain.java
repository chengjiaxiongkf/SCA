package com.yc.gateway;

import com.yc.common.config.filter.UserInfoFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:58 2021/2/9
 */
@SpringBootApplication(scanBasePackages = {"com.yc.gateway","com.yc.common.config.dubbo","com.yc.common.config.redis","com.yc.common.config.common"})
public class AppMain {
    public static void main(String[] args) {
        new SpringApplication(AppMain.class).run(args);
    }
}
