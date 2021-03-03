package com.yc.common.config.datasource.aspect;

import com.alibaba.druid.pool.DruidDataSource;
import com.yc.common.base.data.UserThreadLocal;
import com.yc.common.config.datasource.DynamicDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:58 2021/3/3
 */
@Aspect
@Component
@Order(value = Integer.MIN_VALUE)   //优先级最高，切换完数据源之后再开启本地事务
@Slf4j
public class DynamicDataSourceAspect {

    @Autowired
    private DynamicDataSourceProperties dynamicDataSourceProperties;

    /**
     * Pointcut 切入点
     */
    @Pointcut("@annotation(com.yc.common.config.datasource.aspect.DynamicDataSource)")
    public void pointcut() {
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object obj = null;
        try {
            log.info("start DynamicDataSource...");
            boolean isSwitch = this.switchDataSource(this.getDataSource(point));//切换数据源
            obj = point.proceed();
            if(isSwitch){//使用完之后把切换成功的数据源重置
                this.resetDataSource();//重置数据源(关闭连接，避免内存溢出)
            }
            log.info("end DynamicDataSource...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }

    //获取数据源标识
    private String getDataSource(ProceedingJoinPoint point){
        String key;
        key = this.getUserDataSource();
        if(StringUtils.isNotEmpty(key)){
            return key;
        }
        key = this.getAnnotationDataSource(point);
        if(StringUtils.isNotEmpty(key)){
            return key;
        }
        return key;
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 获取用户的定制数据源标识
     * @Date: Created in 11:07 2021/3/3
     */
    private String getUserDataSource() {
        //如果用户信息有指定数据源则取指定数据源
        if(UserThreadLocal.get()!=null && StringUtils.isNotEmpty(UserThreadLocal.get().getDataSource())){
           return UserThreadLocal.get().getDataSource();
        }
        return null;
    }

    /**
     * @Author: ChengJiaXiong
     * @Description:  获取注解指定数据源（公有云数据读写分离场景）
     * @Date: Created in 11:26 2021/3/3
     */
    private String getAnnotationDataSource(ProceedingJoinPoint point){
        //获取
        String key = null;
        //获取controller对应的方法.
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        //获取方法
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(DynamicDataSource.class)) {
            DynamicDataSource dynamicDataSource = method.getAnnotation(DynamicDataSource.class);
            key = dynamicDataSource.value();
        }
        return key;
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 切换数据源
     * @Date: Created in 11:08 2021/3/3
     * @Params: * @param null
     */
    private boolean switchDataSource(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        final String finalKey = key;
        DruidDataSource druidDataSource = dynamicDataSourceProperties.getDynamicDataSource().stream().filter(obj -> obj.getName().equals(finalKey)).collect(Collectors.toList()).get(0);
        key = druidDataSource.getName();
        if (!com.yc.common.config.datasource.data.DynamicDataSource.dataSourcesMap.containsKey(key)) {//如果没有此数据源配置则加入并刷新targetDataSource
            com.yc.common.config.datasource.data.DynamicDataSource.dataSourcesMap.put(key, druidDataSource);
        }
        com.yc.common.config.datasource.data.DynamicDataSource.setDataSource(key);
        return true;
    }

    /**
     * @Author: ChengJiaXiong
     * @Description: 重置回默认值
     * @Date: Created in 11:09 2021/3/3
     */
    private void resetDataSource() {
        com.yc.common.config.datasource.data.DynamicDataSource.clear();
    }
}
