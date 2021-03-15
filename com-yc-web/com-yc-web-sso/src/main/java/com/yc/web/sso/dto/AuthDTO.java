package com.yc.web.sso.dto;

import com.yc.common.base.dto.BaseResult;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ChengJiaXiong
 * @Description: 授权DTO
 * @Date: Created in 11:45 2021/2/23
 */
@Getter
@Setter
public class AuthDTO extends BaseResult {
    private String method;  //方法
    private String url;     //url
    private String token;   //token
    private Long menuId;    //菜单
    private Long userId;    //用户
}
