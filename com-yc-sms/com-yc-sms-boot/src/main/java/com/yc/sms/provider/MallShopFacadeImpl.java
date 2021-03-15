package com.yc.sms.provider;

import com.yc.common.base.dto.Result;
import com.yc.sms.core.service.SmsService;
import com.yc.sms.facade.SmsFacade;
import com.yc.sms.facade.dto.SmsDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ChengJiaXiong
 * @Description:   发送短信服务
 * @Date: Created in 11:00 2021/3/2
 */
@DubboService
@Slf4j
public class MallShopFacadeImpl implements SmsFacade {
    @Autowired
    private SmsService smsService;

    @Override
    public Result sendSms(SmsDTO smsDTO) {
        return smsService.sendSms(smsDTO);
    }
}

