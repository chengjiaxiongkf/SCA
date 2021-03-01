package com.yc.auth.api.service;

import com.yc.auth.api.dto.AuthDTO;
import com.yc.common.base.dto.Result;
import com.yc.common.base.exception.AuthException;

/**
 * @Author: ChengJiaXiong
 * @Description: 授权接口
 * @Date: Created in 17:47 2021/2/22
 */
public interface AuthService {
    Result authentication(AuthDTO authenticationDTO) throws AuthException;
}
