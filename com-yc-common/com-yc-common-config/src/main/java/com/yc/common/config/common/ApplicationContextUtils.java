package com.yc.common.config.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: ChengJiaXiong
 * @Description: spring上下文操作工具类
 * @Date: Created in 17:26 2021/3/2
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextUtils.applicationContext == null) {
            ApplicationContextUtils.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return ApplicationContextUtils.applicationContext.getBean(clazz);
    }
    public static Object getBean(String name) {
        return ApplicationContextUtils.applicationContext.getBean(name);
    }
    public static String getProperty(String key) {
        return ApplicationContextUtils.applicationContext.getEnvironment().getProperty(key);
    }
}
