package com.yc.auth.provider;

import com.yc.auth.api.dto.AuthDTO;
import com.yc.auth.api.dto.AuthResultDTO;
import com.yc.auth.api.exception.AuthException;
import com.yc.auth.api.facade.AuthFacade;
import com.yc.auth.core.service.InterfaceAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: ChengJiaXiong
 * @Description:  接口授权
 * @Date: Created in 17:48 2021/2/22
 */
@Slf4j
@DubboService
@Repository //防止注入报错
public class InterfaceAuthProviderImpl implements AuthFacade {
    @Autowired
    private InterfaceAuthService interfaceAuthService;

    @Override
    public AuthResultDTO authentication(AuthDTO authDTO) throws AuthException {
        return interfaceAuthService.authentication(authDTO);
    }
}