package com.yc.auth.api.facade;

import com.yc.auth.api.dto.AuthDTO;
import com.yc.auth.api.dto.AuthResultDTO;
import com.yc.common.base.dto.Result;
import com.yc.auth.api.exception.AuthException;

/**
 * @Author: ChengJiaXiong
 * @Description: 授权接口
 * @Date: Created in 17:47 2021/2/22
 */
public interface AuthFacade {
    AuthResultDTO authentication(AuthDTO authDTO) throws AuthException;
}
