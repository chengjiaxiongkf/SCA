package com.yc.auth.core.handler;

import com.alibaba.fastjson.JSONObject;
import com.yc.auth.api.dto.AuthDTO;
import com.yc.common.base.exception.AuthException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 14:52 2021/2/23
 */
@Getter
@Slf4j
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
    public void auth(AuthDTO authenticationDTO) throws AuthException{
        log.info("authenticationDTO:"+ JSONObject.toJSONString(authenticationDTO));
        if(this.checkParams(authenticationDTO)){
            this.process(authenticationDTO);
        }
    }

    protected abstract void process(AuthDTO authDTO) throws AuthException;

    protected abstract boolean checkParams(AuthDTO authDTO) throws AuthException;

    protected enum AuthEnum {
        INTERFACE,
        WX_ID,
        WX_OPEN_ID,
        JI_GUANG,
        HUAN_XIN
    }
}
