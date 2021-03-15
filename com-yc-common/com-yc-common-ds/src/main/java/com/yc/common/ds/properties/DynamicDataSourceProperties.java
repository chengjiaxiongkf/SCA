package com.yc.common.ds.properties;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 10:13 2021/3/3
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class DynamicDataSourceProperties {
    private List<DruidDataSource> dynamicDataSource = new ArrayList<>();
}
