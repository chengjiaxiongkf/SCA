package com.yc.common.config.datasource.data;

import com.yc.common.config.common.ApplicationContextUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ChengJiaXiong
 * @Description:    集成w
 * @Date: Created in 17:34 2021/3/2
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey = ThreadLocal.withInitial(() -> "defaultDataSource");
    public static Map<Object, Object> dataSourcesMap = new ConcurrentHashMap<>();
    static {
        dataSourcesMap.put("defaultDataSource", ApplicationContextUtils.getBean("defaultDataSource"));
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSource.dataSourceKey.get();
    }
    public static void setDataSource(String dataSource) {
        DynamicDataSource.dataSourceKey.set(dataSource);
        DynamicDataSource dynamicDataSource = (DynamicDataSource) ApplicationContextUtils.getBean("dataSource");
        dynamicDataSource.afterPropertiesSet();
    }
    public static String getDataSource() {
        return DynamicDataSource.dataSourceKey.get();
    }
    public static void clear() {
        DynamicDataSource.dataSourceKey.remove();
    }
}
