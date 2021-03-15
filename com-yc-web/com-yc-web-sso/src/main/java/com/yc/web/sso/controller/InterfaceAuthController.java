package com.yc.web.sso.controller;

import com.yc.common.base.dto.Result;
import com.yc.common.base.util.ResultUtil;
import com.yc.common.web.annotation.CurrentUser;
import com.yc.common.web.data.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:23 2021/3/9
 */
@RestController
@RequestMapping("/interfaceAuth")
public class InterfaceAuthController {
    @GetMapping
    public Result<Boolean> interfaceAuth(@CurrentUser User user){
        return ResultUtil.success();
    }
}
