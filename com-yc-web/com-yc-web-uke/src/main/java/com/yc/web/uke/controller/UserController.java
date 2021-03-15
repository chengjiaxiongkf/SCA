package com.yc.web.uke.controller;

import com.yc.common.base.dto.Result;
import com.yc.common.base.util.ResultUtil;
import com.yc.common.web.annotation.CurrentUser;
import com.yc.common.web.data.User;
import com.yc.user.facade.UserFacade;
import com.yc.web.uke.converter.UserConverter;
import com.yc.web.uke.vo.UserVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 17:35 2021/3/4
 */
@RestController
@RequestMapping("user")
public class UserController {
    @DubboReference
    private UserFacade userFacade;
    @Resource
    private UserConverter userConverter;

    @GetMapping("select/{id}")
    public Result<UserVO> getById(@PathVariable long id, @CurrentUser User user) {
        return ResultUtil.success(userConverter.converUserEntity(userFacade.getById(id)));
    }
}
