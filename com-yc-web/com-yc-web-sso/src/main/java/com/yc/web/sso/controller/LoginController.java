package com.yc.web.sso.controller;

import com.yc.common.base.dto.Result;
import com.yc.common.base.util.ResultUtil;
import com.yc.common.util.AssertUtils;
import com.yc.web.sso.dto.LoginDTO;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 19:56 2021/3/9
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public Result login(@RequestBody LoginDTO loginDTO){
        this.checkParams(loginDTO);
        return ResultUtil.success();
    }
    private void checkParams(LoginDTO loginDTO){
        AssertUtils.isBlank(loginDTO.getAction(),"action is null");
        AssertUtils.isBlank(loginDTO.getPassword(),"password is null");
        AssertUtils.isBlank(loginDTO.getPhone(),"phone is null");
        AssertUtils.isBlank(loginDTO.getPlatform(),"platform is null");
        AssertUtils.isBlank(loginDTO.getSign(),"sign is null");
        AssertUtils.isBlank(loginDTO.getTimestamp(),"timestamp is null");
    }
}
