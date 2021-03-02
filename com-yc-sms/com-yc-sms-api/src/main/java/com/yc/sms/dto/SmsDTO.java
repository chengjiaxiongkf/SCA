package com.yc.sms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: ChengJiaXiong
 * @Description:    短信实体
 * @Date: Created in 10:36 2021/3/2
 */
@Getter
@Setter
public class SmsDTO {
    private List<Object> phoneList;
    private String message; //JSON字符串格式替换模板内容占位符
    private String templateCode;
}
