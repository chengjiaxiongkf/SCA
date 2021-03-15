package com.yc.sms.core.service;

import com.aliyuncs.exceptions.ClientException;
import com.yc.common.base.dto.Result;
import com.yc.common.base.util.ResultUtil;
import com.yc.sms.core.config.AliyunSmsConfig;
import com.yc.sms.facade.SmsFacade;
import com.yc.sms.facade.dto.SmsDTO;
import com.yc.sms.facade.exception.SmsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ChengJiaXiong
 * @Description:   发送短信服务
 * @Date: Created in 11:00 2021/3/2
 */
@Slf4j
@Service
public class SmsService {
    @Autowired
    private AliyunSmsConfig aliyunSmsConfig;

    public Result sendSms(SmsDTO smsDTO) {
        try{
            aliyunSmsConfig.sendMsg(smsDTO.getPhoneList(),smsDTO.getMessage(),smsDTO.getTemplateCode());
        }catch(SmsException | ClientException e){
            log.error(e.getMessage(),e);
            return ResultUtil.failed(e.getMessage());
        }
        return ResultUtil.success();
    }
}

