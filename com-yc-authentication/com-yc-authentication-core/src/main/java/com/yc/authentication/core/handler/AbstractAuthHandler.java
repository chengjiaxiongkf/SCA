package com.yc.authentication.core.handler;

import com.yc.authentication.api.dto.AuthenticationDTO;
import com.yc.authentication.api.exception.AuthException;
import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 14:52 2021/2/23
 */
@Getter
public abstract class AbstractAuthHandler {
    private AuthEnum authEnum;
    public AbstractAuthHandler(){
        this.authEnum = type();
    }
    public abstract AuthEnum type();

    /**
     * @Author: ChengJiaXiong
     * @Description: 自定义校验 true通过 false不通过
     * @Date: Created in 11:52 2021/2/18
     */
    public boolean auth(AuthenticationDTO authenticationDTO) throws AuthException{
        if(!this.checkParams(authenticationDTO)){
            return false;
        }
        return this.check(authenticationDTO);
    }

    protected abstract boolean check(AuthenticationDTO authenticationDTO) throws AuthException;

    protected abstract boolean checkParams(AuthenticationDTO authenticationDTO) throws AuthException;

    protected enum AuthEnum {
        INTERFACE,
        WX_ID,
        WX_OPEN_ID,
        JI_GUANG,
        HUAN_XIN
    }
}
