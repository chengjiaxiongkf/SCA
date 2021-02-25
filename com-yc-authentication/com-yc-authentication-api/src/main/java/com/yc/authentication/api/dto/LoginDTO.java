package com.yc.authentication.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 18:17 2021/2/23
 */
@Getter
@Setter
@ToString
public class LoginDTO {
    private String action;
    private String phone;
    private String platform;
    private String sign;
    private String timestamp;
}
