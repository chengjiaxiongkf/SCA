package com.yc.sms.facade;

import com.yc.common.base.dto.Result;
import com.yc.sms.facade.dto.SmsDTO;

/**
 * @Author: ChengJiaXiong
 * @Description: 发送短信服务
 * @Date: Created in 10:21 2021/3/2
 */
public interface SmsFacade {
    Result sendSms(SmsDTO smsDTO);
}
