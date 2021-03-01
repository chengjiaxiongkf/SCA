package com.yc.auth.core.controller;

import com.yc.auth.api.dto.LoginDTO;
import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultCodeEnum;
import com.yc.common.base.dto.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ChengJiaXiong
 * @Description: 登录服务
 * @Date: Created in 18:13 2021/2/23
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @PostMapping
    public Result login(@RequestBody LoginDTO loginDTO){
        log.info(loginDTO.toString());
        return ResultUtil.success(ResultCodeEnum.SUCCESS);
    }
}
