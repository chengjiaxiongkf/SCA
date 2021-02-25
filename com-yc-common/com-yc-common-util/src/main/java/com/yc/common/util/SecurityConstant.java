package com.yc.common.util;

/**
 * @author SanGang
 */
public class SecurityConstant {
    /**
     * JWT存储权限前缀
     */
    public static final String AUTHORITY_PREFIX = "ROLE_";

    /**
     * 后台管理client_id
     */
    public static final String ADMIN_CLIENT_ID = "admin-app";

    /**
     * 前台商城client_id
     */
    public static final String PORTAL_CLIENT_ID = "portal-app";

    /**
     * Redis缓存权限规则key
     */
    public static final String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 用户信息Http请求头
     */
    public static final String USER_TOKEN_HEADER = "currentUser";

    /**
     * 认证接口路径匹配
     */
    public static final String OAUTH_URL = "/oauth/";

    /**
     * author参数头
     */
    public static final String ACCESS_TOKEN = "Authorization";

    /**
     * JWT令牌前缀
     */
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    public static final String ACCESS_PREFIX = "access:";
    /**
     * token分割
     */
    public static final String TOKEN_PREFIX = "sangang";
    /**
     * JWT签名加密key
     */
    public static final String JWT_SIGN_KEY = "eac617fa04275377dd4cfd0653c02aa5";

    /**
     * TOKEN_HEADER前缀
     */
    public static final String JWT_TOKEN_HEADER = "header";

    /**
     * TOKEN参数名
     */
    public static final String JWT_TOKEN = "x-auth-token";

    /**
     * SOURCE参数名
     */
    public static final String SOURCE = "X-Client-Type";

    /**
     * MENU_ID 按钮ID
     */
    public static final String MENU_ID = "menu_id";

    public static final String DEFAULT_SHOP_NO = "0";

    /**
     * author的token有效期
     */
    public static final Integer OAUTH_OFFSET_DAY = 1;
    /**
     * C端用户的token有效期
     */
    public static final Integer C_OFFSET_DAY = 7;
    /**
     * B端用户的token有效期
     */
    public static final Integer B_OFFSET_DAY = 30;
    /**
     * 系统固定不进行认证，直接放行的URL，供WebSecurityConfig、ResourceServerConfig公用
     */
    public static final String[] PATTERN_URLS = {
            "/instances",
            "/actuator/**",
            "/druid/**",
            "/assets/**",
            "/webjars/**",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html",
            "/docs.html",
            "/doc.html",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/favicon.ico"
    };
}
