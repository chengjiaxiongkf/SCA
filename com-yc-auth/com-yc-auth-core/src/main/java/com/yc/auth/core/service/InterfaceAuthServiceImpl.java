package com.yc.auth.core.service;

import com.alibaba.fastjson.JSONObject;
import com.yc.auth.api.dto.AuthDTO;
import com.yc.auth.api.service.AuthService;
import com.yc.auth.core.handler.InterfaceAuthHandler;
import com.yc.common.base.dto.Result;
import com.yc.common.base.enums.AuthExceptionCodeEnum;
import com.yc.common.base.exception.AuthException;
import com.yc.common.base.dto.ResultCodeEnum;
import com.yc.common.base.dto.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 17:48 2021/2/22
 */
@Slf4j
@DubboService(group = "interfaceAuthentication")
@Repository //防止注入报错
public class InterfaceAuthServiceImpl implements AuthService {
    @Autowired
    private InterfaceAuthHandler interfaceAuthHandler;

    @Override
    public Result authentication(AuthDTO authDTO) {
        try{
            log.info("authenticationing... start.");
            interfaceAuthHandler.auth(authDTO);
        }catch(AuthException e){
            //URL没有权限 或者 TOKEN过期不存在不应记录error级别日志
            if(e.getAuthExceptionCodeEnum() == AuthExceptionCodeEnum.URL_AUTH_FAIL
                || e.getAuthExceptionCodeEnum() == AuthExceptionCodeEnum.TOKEN_NO_EXIST){
                log.warn("authentication warn:"+e.getMessage());
            }else{
                log.error("authentication error:"+e.getMessage());
            }
            return ResultUtil.failed(ResultCodeEnum.FORBIDDEN);
        }
        return ResultUtil.success();
    }
}
