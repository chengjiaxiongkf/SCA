package com.yc.authentication.api.service;

import com.yc.common.base.dto.Result;
import com.yc.authentication.api.dto.AuthenticationDTO;
import com.yc.common.base.exception.AuthException;

/**
 * @Author: ChengJiaXiong
 * @Description: 授权接口
 * @Date: Created in 17:47 2021/2/22
 */
public interface AuthenticationService {
    Result authentication(AuthenticationDTO authenticationDTO) throws AuthException;
}
