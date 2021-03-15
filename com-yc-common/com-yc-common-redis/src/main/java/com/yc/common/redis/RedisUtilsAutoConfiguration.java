package com.yc.common.redis;

import com.yc.common.redis.config.RedisConfig;
import com.yc.common.redis.util.RedisUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:41 2021/3/4
 */
@Configuration
@ConditionalOnClass(RedisUtils.class)// 存在这个类才装配当前类
@ConditionalOnProperty(name = "spring.redis.host")//配置文件存在spring.redis才启动，必须存在该配置
public class RedisUtilsAutoConfiguration implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{RedisUtils.class.getName(),RedisConfig.class.getName()};
    }
}
