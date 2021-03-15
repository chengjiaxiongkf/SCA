package com.yc.common.ds.annotation;

import java.lang.annotation.*;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:55 2021/3/3
 */
@Target({ElementType.METHOD})//Annotation所修饰的对象范围:方法参数
@Retention(RetentionPolicy.RUNTIME)//Annotation被保留时间:运行时保留(有效)
@Documented//标记注解:java工具文档化
public @interface DynamicDataSource {
    String value() default "";
}
