package com.yc.user.core.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.common.base.data.User;
import com.yc.common.base.data.UserThreadLocal;
import com.yc.common.base.dto.Result;
import com.yc.common.base.enums.UserExceptionCodeEnum;
import com.yc.common.base.exception.UserException;
import com.yc.common.base.dto.ResultUtil;
import com.yc.common.config.datasource.DynamicDataSourceProperties;
import com.yc.common.config.datasource.data.DynamicDataSource;
import com.yc.user.api.dto.UserDTO;
import com.yc.user.api.service.UserService;
import com.yc.user.core.converter.UserConverter;
import com.yc.user.core.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户服务
 * @Date: Created in 16:51 2021/2/7
 */
@RestController
@RequestMapping("/")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private DynamicDataSourceProperties dynamicDataSourceProperties;

    //新增
    @PostMapping(value = "insert")
    public Result insert(UserDTO userDTO)  {
        userService.insert(userDTO);
        return ResultUtil.success("新增成功");
    }

   //通过ID更新
    @PutMapping(value = "update/{id}")
    public Result updateById(@PathVariable Long id,UserDTO userDTO)  {
        userDTO.setId(id);
        userService.updateById(userDTO);
        return ResultUtil.success("修改成功");
    }

   //通过ID更头像
    @PutMapping(value = "update/headimg/{id}")
    public Result<UserVO> updateHeadimgById(@PathVariable Long id, String headimg)  {
//        AssertUtils.notNull(headimg, "头像不能为空");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setHeadimg(headimg);
        //TODO 待实现
        return ResultUtil.success(new UserVO(),"头像更改成功!");
    }

    //通过ID更昵称
    @PutMapping(value = "update/nickname/{id}")
    public Result<UserVO> updateNicknameById(@PathVariable Long id,String nickname) {
//        AssertUtils.notNull(nickname, "昵称不能为空");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setNickname(nickname);
        //TODO 待实现
        return ResultUtil.success(new UserVO(),"昵称更改成功!");
    }

    //通过ID更性别
    @PutMapping(value = "update/sex/{id}")
    public Result<UserVO> updateSexById(@PathVariable Long id,Integer sex)  {
//        AssertUtils.notNull(sex, "性别不能为空");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setSex(sex);
        return ResultUtil.success(new UserVO(),"性别更改成功!");
    }

    //查询根据ID
    @GetMapping(value = "select/{id}")
    public Result<UserVO> getById(@PathVariable Long id)  {
        //测试数据
        UserThreadLocal.set(new User());
        UserThreadLocal.get().setDataSource("dev");
        return ResultUtil.success(userConverter.converUserVO(userService.getById(id)));
    }

    //查询根据ID
    @GetMapping(value = "selects/{id}")
    public Result<UserVO> getByIds(@PathVariable Long id)  {
        //测试数据
        UserThreadLocal.set(new User());
        UserThreadLocal.get().setDataSource("test");
        return ResultUtil.success(userConverter.converUserVO(userService.getById(id)));
    }

    //查询分页
    @GetMapping(value = "select/page")
    public Result<Page<UserVO>> selectPage(Page page, UserDTO userDTO)  {
        //TODO 不传参会导致查全表
        return ResultUtil.success(userConverter.converUserVOPage(userService.page(page,userDTO)),"查询成功");
    }

    //查询全部
    @GetMapping(value = "select/list")
    public Result<List<UserVO>> selectList(UserDTO userDTO)  {
        //TODO 不传参会导致查全表
        return ResultUtil.success(userConverter.converUserVOList(userService.list(userDTO)),"查询成功");
    }

    //通过ID删除
    @DeleteMapping(value = "delete/{id}")
    public Result deleteById(@PathVariable Long id)  {
        return ResultUtil.success(null,"删除成功!");
    }

    //通过ids批量删除
    @DeleteMapping(value = "deleteBatch")
    public Result deleteBatch(String ids)  {
        return ResultUtil.success(null,"删除成功!");
    }
}
