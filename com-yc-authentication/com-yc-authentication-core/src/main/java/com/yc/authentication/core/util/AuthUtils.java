package com.yc.authentication.core.util;

import com.yc.authentication.core.config.AuthRefreshProperties;
import org.springframework.util.AntPathMatcher;


/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:55 2021/2/23
 */
public class AuthUtils {
    /**
     * @Author: ChengJiaXiong
     * @Description: 只校验U客后台/U客APP
     * @Retun: true 需要校验 false 不需要校验
     * @Date: Created in 19:01 2021/2/19
     */
    public static boolean checkPlatform(AuthRefreshProperties globalRefreshProperties, int platform){
        return !globalRefreshProperties.getPlatforms().contains(platform);
    }
}
