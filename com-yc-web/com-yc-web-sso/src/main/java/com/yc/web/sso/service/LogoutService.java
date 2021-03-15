package com.yc.web.sso.service;

import com.yc.common.redis.util.RedisUtils;
import com.yc.web.sso.exception.SsoException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description: 注销登录
 * @Date: Created in 20:16 2021/3/9
 */
@Service
public class LogoutService {
    @Resource
    private RedisUtils redisUtils;

    public void logout() throws SsoException {

    }
}
