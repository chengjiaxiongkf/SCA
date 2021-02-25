package com.yc.mall.core.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yc.mall.api.dto.MallShopDTO;
import com.yc.mall.api.service.MallShopService;
import com.yc.mall.core.converter.MallShopConverter;
import com.yc.mall.core.dao.MallShopDao;
import com.yc.mall.core.entity.MallShopEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:41 2021/2/18
 */
@DubboService
@Slf4j
@Repository
public class MallShopServiceImpl implements MallShopService {

    @Resource
    private MallShopDao mallShopDao;

    @Autowired
    private MallShopConverter mallShopConverter;

    @Override
    public MallShopDTO getOne(MallShopDTO mallShopDTO) {
        LambdaQueryWrapper<MallShopEntity> queryWrapper = new LambdaQueryWrapper<MallShopEntity>()
                .eq(MallShopEntity::getOrganizeId, mallShopDTO.getOrganizeId());
        return mallShopConverter.converMallShopDTO(mallShopDao.selectOne(queryWrapper));
    }
}
