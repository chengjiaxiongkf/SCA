package com.yc.sms.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultUtil;
import com.yc.sms.api.SmsService;
import com.yc.sms.dto.SmsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 13:42 2021/3/2
 */
@RestController
@RequestMapping("/")
@Slf4j
public class SmsController {
    @Autowired
    private SmsService smsService;

    @PostMapping("/sendSms")
    public Result sendSms(@RequestParam("telephone")String telephone, @RequestParam("message")String message, @RequestParam("templateCode")String templateCode){
        SmsDTO smsDTO = new SmsDTO();
        smsDTO.setPhoneList(Arrays.asList(telephone));
        smsDTO.setMessage(message);
        smsDTO.setTemplateCode(templateCode);
        log.info("params:" + JSONObject.toJSONString(smsDTO));
        smsService.sendSms(smsDTO);
        return ResultUtil.success();
    }
}
