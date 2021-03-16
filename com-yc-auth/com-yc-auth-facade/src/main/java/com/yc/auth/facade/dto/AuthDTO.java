package com.yc.auth.facade.dto;

import com.yc.common.base.dto.BaseResult;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:45 2021/2/23
 */
@Getter
@Setter
public class AuthDTO extends BaseResult {
    private String method;
    private String url;
    private String token;
    private Long menuId;
    private Long userId;
}
