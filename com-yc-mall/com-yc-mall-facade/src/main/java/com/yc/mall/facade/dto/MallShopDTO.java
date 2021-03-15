package com.yc.mall.facade.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ChengJiaXiong
 * @Description:  商城 - 店铺表服务传输实体
 * @Date: Created in 15:39 2021/2/18
 */
@Getter
@Setter
public class MallShopDTO implements Serializable {
   //主键ID
   private Long id;

   //企业id
   private Integer organizeId;

   //商店编号
   private String shopNo;

   //商店名称
   private String shopName;

   //商店背景图
   private String bgImg;

   //入口名称
   private String entryName;

   //可售后天数
   private Integer asHoldDays;

   //客服联系人
   private String csContact;

   //客服电话号码
   private String csPhoneNumber;

   //客服微信号
   private String csWeixinNo;

   //商店状态 10: 正常  20：过期
   private Integer status;

   //创建时间
   private Date createdTime;

   //修改时间
   private Date updatedTime;

   //商店经营状态 10:营业  20:休息 30:待启用
   private Integer operateStatus;
}
