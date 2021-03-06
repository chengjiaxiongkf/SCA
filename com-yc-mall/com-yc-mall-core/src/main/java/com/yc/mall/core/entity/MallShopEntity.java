package com.yc.mall.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: ChengJiaXiong
 * @Description: 商城店铺表
 * @Date: Created in 11:04 2021/2/24
 */
@Getter
@Setter
@TableName("mall_shop")
public class MallShopEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Integer organizeId;//企业id
    private String shopNo;//商店编号
    private String shopName;//商店名称
    private String bgImg;//商店背景图
    private String entryName;//入口名称
    private Integer asHoldDays; //可售后天数
    private String csContact; //客服联系人
    private String csPhoneNumber;//客服电话号码
    private String csWeixinNo; //客服微信号
    private Integer status;//商店状态 10: 正常  20：过期
    private Date createdTime;
    private Date updatedTime;
    private Integer operateStatus;//商店经营状态 10:营业  20:休息 30:待启用
}
