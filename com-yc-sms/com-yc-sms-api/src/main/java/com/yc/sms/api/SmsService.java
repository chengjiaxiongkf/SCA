package com.yc.sms.api;

import com.yc.common.base.dto.Result;
import com.yc.sms.dto.SmsDTO;

/**
 * @Author: ChengJiaXiong
 * @Description: 发送短信服务
 * @Date: Created in 10:21 2021/3/2
 */
public interface SmsService {
    Result sendSms(SmsDTO smsDTO);
}
