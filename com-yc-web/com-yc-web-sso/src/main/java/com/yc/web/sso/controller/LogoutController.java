package com.yc.web.sso.controller;

import com.yc.common.base.dto.Result;
import com.yc.common.base.util.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 20:15 2021/3/9
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {

    @PostMapping
    public Result logout(){
        return ResultUtil.success();
    }
}
