package com.yc.common.redis.annotation;

import com.yc.common.redis.RedisUtilsAutoConfiguration;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:49 2021/3/4
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RedisUtilsAutoConfiguration.class)
public @interface EnableRedisUtils {
}
