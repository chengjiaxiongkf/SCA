package com.yc.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author chengjiaxiong
 * @Date 2020/7/29 0:36
 */
@Configuration
public class RestConfig {
    /**
     * ribbon负载均衡(@LoadBalanced默认轮训算法)
     * //通过RestTemplate来实现调用接口
     * 表示RestTemplate开启了负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate ribbonRestTemplate(){
        return  new RestTemplate();
    }
    //重新创建一个均衡策略，表示不使用默认的轮训,new RandomRule()为随机调用负载均衡器
//    @Bean
//    public IRule getIReule(){ //通过获取一个IRule对象，
//        return  new RandomRule();  //达到的目的，用我们重新选择的随机，替代默认的轮训方式
//    }
}
