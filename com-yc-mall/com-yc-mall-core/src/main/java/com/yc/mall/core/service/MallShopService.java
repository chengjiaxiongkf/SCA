package com.yc.mall.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yc.mall.core.converter.MallShopConverter;
import com.yc.mall.core.entity.MallShop;
import com.yc.mall.core.mapper.MallShopMapper;
import com.yc.mall.facade.dto.MallShopDTO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 17:27 2021/3/4
 */
@Service
public class MallShopService {
    @Resource
    private MallShopMapper mallShopDao;

    @Resource
    private MallShopConverter mallShopConverter;

    public MallShopDTO getOne(MallShopDTO mallShopDTO) {
        LambdaQueryWrapper<MallShop> queryWrapper = new LambdaQueryWrapper<MallShop>()
                .eq(MallShop::getOrganizeId, mallShopDTO.getOrganizeId());
        return mallShopConverter.converMallShopDTO(mallShopDao.selectOne(queryWrapper));
    }
}
