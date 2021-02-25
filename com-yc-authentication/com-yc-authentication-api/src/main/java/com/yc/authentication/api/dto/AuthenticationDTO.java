package com.yc.authentication.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:45 2021/2/23
 */
@Getter
@Setter
public class AuthenticationDTO implements Serializable {
    private String method;
    private String url;
    private String token;
    private Long menuId;
    private Long userId;
}
