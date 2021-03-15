package com.yc.mall.provider;

import com.yc.mall.core.service.MallShopService;
import com.yc.mall.facade.dto.MallShopDTO;
import com.yc.mall.facade.MallShopFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 15:41 2021/2/18
 */
@DubboService
@Slf4j
public class MallShopFacadeImpl implements MallShopFacade {
    @Autowired
    private MallShopService mallShopService;

    @Override
    public MallShopDTO getOne(MallShopDTO mallShopDTO) {
        return mallShopService.getOne(mallShopDTO);
    }
}
