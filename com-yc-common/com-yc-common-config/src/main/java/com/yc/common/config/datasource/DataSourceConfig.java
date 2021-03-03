package com.yc.common.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.yc.common.config.datasource.data.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:07 2021/3/3
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource defaultDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @Primary
    @DependsOn({"applicationContextUtils", "defaultDataSource"})
    public DynamicDataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(DynamicDataSource.dataSourcesMap);
        return dynamicDataSource;
    }
}
