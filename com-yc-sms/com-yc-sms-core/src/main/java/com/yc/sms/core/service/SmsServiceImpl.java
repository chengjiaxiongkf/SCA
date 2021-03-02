package com.yc.sms.core.service;

import com.aliyuncs.exceptions.ClientException;
import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultUtil;
import com.yc.common.base.exception.SmsException;
import com.yc.sms.api.SmsService;
import com.yc.sms.core.util.AliyunSmsUtils;
import com.yc.sms.dto.SmsDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:00 2021/3/2
 */
@DubboService
@Slf4j
@Repository
public class SmsServiceImpl implements SmsService {
    @Autowired
    private AliyunSmsUtils aliyunSmsUtils;

    @Override
    public Result sendSms(SmsDTO smsDTO) {
        try{
            aliyunSmsUtils.sendMsg(smsDTO.getPhoneList(),smsDTO.getMessage(),smsDTO.getTemplateCode());
        }catch(SmsException | ClientException e){
            log.error(e.getMessage(),e);
            return ResultUtil.failed(e.getMessage());
        }
        return ResultUtil.success();
    }
}

