package com.yc.authentication.core.service;

import com.alibaba.fastjson.JSONObject;
import com.yc.authentication.api.dto.AuthenticationDTO;
import com.yc.authentication.api.service.AuthenticationService;
import com.yc.authentication.core.handler.InterfaceAuthHandler;
import com.yc.common.base.dto.Result;
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
public class InterfaceAuthServiceImpl implements AuthenticationService {
    @Autowired
    private InterfaceAuthHandler interfaceAuthHandler;

    @Override
    public Result authentication(AuthenticationDTO authenticationDTO) {
        try{
            log.info("authenticationing...");
            log.info("authenticationDTO:"+ JSONObject.toJSONString(authenticationDTO));
            if(!interfaceAuthHandler.auth(authenticationDTO)){
                return ResultUtil.failed(ResultCodeEnum.FORBIDDEN);
            }
        }catch(AuthException e){
            log.warn("authentication error:",e.getMessage());//授权异常有可能只是token过期
            return ResultUtil.failed(ResultCodeEnum.FORBIDDEN);
        }
        return ResultUtil.success(ResultCodeEnum.SUCCESS);
    }
}
