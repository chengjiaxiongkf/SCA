package com.yc.auth.facade;

import com.yc.auth.facade.dto.AuthDTO;
import com.yc.auth.facade.exception.AuthException;

/**
 * @Author: ChengJiaXiong
 * @Description: 授权接口
 * @Date: Created in 17:47 2021/2/22
 */
public interface AuthFacade {
    AuthDTO authentication(AuthDTO authDTO) throws AuthException;
}
