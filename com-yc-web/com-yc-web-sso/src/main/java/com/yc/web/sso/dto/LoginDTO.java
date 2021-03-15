package com.yc.web.sso.dto;

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
    private Integer action;
    private String password;
    private Integer phone;
    private Integer platform;
    private String sign;
    private Long timestamp;
}
