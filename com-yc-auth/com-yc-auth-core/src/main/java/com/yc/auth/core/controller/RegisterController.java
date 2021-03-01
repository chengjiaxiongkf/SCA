package com.yc.auth.core.controller;

import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ChengJiaXiong
 * @Description:    注册服务
 * @Date: Created in 16:26 2021/3/1
 */
@Slf4j
@RestController
@RequestMapping("/register")
public class RegisterController {
    @PostMapping
    public Result regist(@RequestParam("userName")String userName,@RequestParam("password")String password){
        return ResultUtil.success();
    }
}
