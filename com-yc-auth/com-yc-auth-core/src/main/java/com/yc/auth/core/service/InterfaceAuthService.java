package com.yc.auth.core.service;

import com.yc.auth.facade.dto.AuthDTO;
import com.yc.auth.facade.dto.AuthResultDTO;
import com.yc.auth.facade.exception.AuthException;
import com.yc.auth.core.handler.InterfaceAuthHandler;
import com.yc.common.base.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 18:47 2021/3/4
 */
@Service
@Slf4j
public class InterfaceAuthService {
    @Autowired
    private InterfaceAuthHandler interfaceAuthHandler;

    public AuthDTO authentication(AuthDTO authDTO) {
        AuthDTO authResultDTO = null;
        try{
            authResultDTO = new AuthDTO();
            log.info("authenticationing... start.");
            interfaceAuthHandler.auth(authDTO);
            log.info("authenticationing... end.");
        }catch(AuthException e){
            log.info("authentication fail:"+e.getMessage());
            if(authResultDTO==null){
                authResultDTO = new AuthDTO();
            }
            authResultDTO.setCode(ResultCodeEnum.FORBIDDEN.getCode());
            authResultDTO.setMsg(ResultCodeEnum.FORBIDDEN.getMessage());
            return authResultDTO;
        }
        authResultDTO.setCode(ResultCodeEnum.SUCCESS.getCode());
        authResultDTO.setMsg(ResultCodeEnum.SUCCESS.getMessage());
        return authResultDTO;
    }
}
