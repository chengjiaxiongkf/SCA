package com.yc.gateway.controller;

import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ChengJiaXiong
 * @Description: 服务熔断请求入口
 * @Date: Created in 17:29 2021/2/18
 */
@RestController
@Slf4j
public class GatewayFallbackController {

    @Bean
    @RequestMapping("/defaultfallback")
    public Result routerFunction() {
        return ResultUtil.failed("服务降级");
    }

}
