package com.yc.mall.core.converter;

import com.yc.mall.api.dto.MallShopDTO;
import com.yc.mall.core.entity.MallShopEntity;
import com.yc.mall.vo.MallShopVO;
import org.mapstruct.Mapper;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 11:22 2021/2/24
 */
@Mapper(componentModel="spring")
public interface MallShopConverter {
    MallShopDTO converMallShopDTO(MallShopEntity mallShopEntity);
    MallShopVO converMallShopVO(MallShopDTO mallShopDTO);
}
