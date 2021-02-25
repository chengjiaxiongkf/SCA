package com.yc.mall.core.controller;

import com.yc.common.base.dto.Result;
import com.yc.common.base.util.ResultUtil;
import com.yc.mall.api.dto.MallShopDTO;
import com.yc.mall.api.service.MallShopService;
import com.yc.mall.core.converter.MallShopConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 12:20 2021/2/24
 */
@RestController
@RequestMapping("/mallShop")
public class MallShopController {
    @Autowired
    private MallShopService mallShopService;

    @Resource
    private MallShopConverter mallShopConverter;

    @GetMapping("/{organizeId}")
    public Result getMallShop(@PathVariable Integer organizeId){
        MallShopDTO mallShopDTO = new MallShopDTO();
        mallShopDTO.setOrganizeId(organizeId);
        return ResultUtil.success(mallShopConverter.converMallShopVO(mallShopService.getOne(mallShopDTO)));
    }
}
