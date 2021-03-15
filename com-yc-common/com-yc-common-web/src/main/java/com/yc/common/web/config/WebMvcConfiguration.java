package com.yc.common.web.config;

import com.yc.common.web.filter.InterceptorUserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * webmvc配置
 * @author: WangYang
 * @Date: 2020/9/22 10:22
 * @Description:
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Resource
    private InterceptorUserInfo interceptorUserInfo;
    @Resource
    private CurrentUserArgReslover currentUserArgReslover;

    //跨域配置
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);/* 是否允许请求带有验证信息 */
        corsConfiguration.addAllowedOrigin("*");/* 允许访问的客户端域名 */
        corsConfiguration.addAllowedHeader("*");/* 允许服务端访问的客户端请求头 */
        corsConfiguration.addAllowedMethod("*");/* 允许访问的方法名,GET POST等 */
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //放行的请求
        List<String> patterns = new ArrayList<>();
        patterns.add("/error"); //请求数据或路径错误时springmvc自发的请求
        registry.addInterceptor(interceptorUserInfo).addPathPatterns("/**").excludePathPatterns(patterns);//用户信息封装
    }
    //参数注入器
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserArgReslover);
        resolvers.add(new CurrentUserArgReslover());
    }
}