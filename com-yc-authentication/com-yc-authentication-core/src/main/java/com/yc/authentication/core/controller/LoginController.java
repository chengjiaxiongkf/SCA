package com.yc.authentication.core.controller;

import com.yc.authentication.api.dto.LoginDTO;
import com.yc.common.base.dto.Result;
import com.yc.common.base.dto.ResultCodeEnum;
import com.yc.common.base.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ChengJiaXiong
 * @Description:
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
