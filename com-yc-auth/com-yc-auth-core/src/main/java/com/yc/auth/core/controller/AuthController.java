package com.yc.auth.core.controller;

import com.yc.auth.api.dto.AuthDTO;
import com.yc.auth.core.service.InterfaceAuthServiceImpl;
import com.yc.common.base.dto.Result;
import com.yc.common.util.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChengJiaXiong
 * @Description: 授权http入口
 * @Date: Created in 11:02 2021/2/23
 */
@RestController
@RequestMapping("/")
@Slf4j
public class AuthController {
    @Autowired
    private InterfaceAuthServiceImpl interfaceAuthService;

    @GetMapping
    public Result getInterfaceAuthentication(@RequestParam("url") String url, HttpServletRequest request){
        String token = request.getHeader(SecurityConstant.JWT_TOKEN);
        String menuId = request.getHeader(SecurityConstant.MENU_ID);
        String method = request.getMethod();
        AuthDTO authDTO = new AuthDTO();
        authDTO.setToken(token);
        authDTO.setMenuId(Long.parseLong(menuId));
        authDTO.setMethod(method);
        authDTO.setUrl(url);
        return interfaceAuthService.authentication(authDTO);
    }
}
