package com.yc.common.ds.handler;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ChengJiaXiong
 * @Description:  数据源切换
 * @Date: Created in 17:34 2021/3/2
 */
public class DynamicDataSourceHandler extends AbstractRoutingDataSource implements ApplicationContextAware  {

    private static final ThreadLocal<String> dataSourceKey = ThreadLocal.withInitial(() -> "defaultDataSource");
    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>();
    static {
        dataSourcesMap.put("defaultDataSource", DynamicDataSourceHandler.applicationContext.getBean("defaultDataSource"));
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHandler.dataSourceKey.get();
    }
    public static void setDataSource(String dataSource) {
        DynamicDataSourceHandler.dataSourceKey.set(dataSource);
        DynamicDataSourceHandler dynamicDataSource = (DynamicDataSourceHandler) DynamicDataSourceHandler.applicationContext.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
    }
    public static String getDataSource() {
        return DynamicDataSourceHandler.dataSourceKey.get();
    }
    public static void remove() {
        DynamicDataSourceHandler.dataSourceKey.remove();
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContext==null){
            DynamicDataSourceHandler.applicationContext = applicationContext;
        }
    }
}
