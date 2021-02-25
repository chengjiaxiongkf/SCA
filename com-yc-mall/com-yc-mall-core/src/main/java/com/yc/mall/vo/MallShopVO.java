package com.yc.mall.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ChengJiaXiong
 * @Description:
 * @Date: Created in 12:22 2021/2/24
 */
@Getter
@Setter
public class MallShopVO implements Serializable {
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
