package com.yc.organize.facade.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * @Description 组织表 网络传输实体
 * </p>
 * @author chengjiaxiong
 * @since 2021-03-16
 * @version 1.0
 */
@Getter
@Setter
public class OrganizeDTO implements Serializable {
   /**
   * 组织id
   */
 private Integer id;
   /**
   * 组织类型 1-企业 2-组织 3-政府
   */
 private Boolean type;
   /**
   * 组织短名称
   */
 private String shortName;
   /**
   * 组织全称
   */
 private String fullName;
   /**
   * 省份
   */
 private String province;
   /**
   * 省id
   */
 private Integer provinceId;
   /**
   * 市
   */
 private String city;
   /**
   * 市id
   */
 private Integer cityId;
   /**
   * 区
   */
 private String area;
   /**
   * 区id
   */
 private Integer areaId;
   /**
   * 地址
   */
 private String address;
   /**
   * 区域编码
   */
 private Integer regionCode;
   /**
   * 一级分类id
   */
 private Integer firstClassifyId;
   /**
   * 行业一级分类
   */
 private String firstClassify;
   /**
   * 二级分类id
   */
 private Integer secondClassifyId;
   /**
   * 行业二级分类
   */
 private String secondClassify;
   /**
   * 经度
   */
 private String longitude;
   /**
   * 纬度
   */
 private String latitude;
   /**
   * 企业基本联系人
   */
 private String baseContactName;
   /**
   * 联系电话
   */
 private String phone;
   /**
   * 邮箱
   */
 private String email;
   /**
   * 企业logo
   */
 private String logo;
   /**
   * 企业介绍
   */
 private String profile;
   /**
   * 企业官网二维码
   */
 private String officialQrcode;
   /**
   * 企业规模 1：少于15人  2：15-50 人 3：50-150人  4：150-500人 5：500-2000人 6：2000人以上
   */
 private Boolean scale;
   /**
   * 营业执照图片地址
   */
 private String businessLicense;
   /**
   * 营业注册号
   */
 private String businessLicenseCode;
   /**
   * 营业执照开始日期
   */
 private Long businessLicenseStart;
   /**
   * 营业执照结束日期
   */
 private Long businessLicenseEnd;
   /**
   * 企业联系人
   */
 private String contactName;
   /**
   * 企业联系人电话
   */
 private String contactPhone;
   /**
   * 授权书地址
   */
 private String authFile;
   /**
   * 名片购买总数
   */
 private Integer cardNum;
   /**
   * 名片剩余数
   */
 private Integer cardRestNum;
   /**
   * 服务开始时间
   */
 private Long serviceStart;
   /**
   * 服务结束时间
   */
 private Long serviceEnd;
   /**
   * 消费金额
   */
 private BigDecimal payedMoney;
   /**
   * 企业云盘大小
   */
 private Integer cloudSize;
   /**
   * 已使用云盘大小
   */
 private Integer cloudUsedSize;
   /**
   * 人工审核开关 ：0-不开启 1-已开启
   */
 private Boolean openAuth;
   /**
   * 验证方式： 1-无需图片验证 2-员工照片验证 3-员工身份证验证 4-员工工牌验证 
   */
 private Boolean authType;
   /**
   * 认证备注
   */
 private String authRemark;
   /**
   * 是否认证 1-未认证 2-认证中 3-已认证  4- 已失效
   */
 private Boolean status;
   /**
   * 是否禁用 0-启用 1-禁用
   */
 private Boolean isDisable;
   /**
   * 禁用备注
   */
 private String disableRemark;
   /**
   * 禁用原因
   */
 private String disableReason;
   /**
   * 是否删除 0-正常 1-已删除
   */
 private Boolean isDel;
   /**
   * 是否加入企业广场  0-不加入 1-加入
   */
 private Boolean isAddSquare;
   /**
   * 禁用时间
   */
 private Integer disabledAt;
   /**
   * 删除时间
   */
 private Integer deletedAt;
   /**
   * 邀请码
   */
 private String inviteCode;
   /**
   * 父级id  由代理商邀请注册的用户所创建的企业都是该代理商的代理企业，成代理商的id为本企业的fid
   */
 private Integer fatherId;
   /**
   * 邀请的企业员工id
   */
 private Integer inviteEmployeeId;
   /**
   * 邀请用户的id
   */
 private Integer inviteUserId;
   /**
   * 邀请前置码的批次号
   */
 private String inviteBatchNumber;
   /**
   * 是否为代理企业  0：否  1：是
   */
 private Boolean isAgent;
   /**
   * 代理商审核状态  0-待审核 1-审核通过 2-审核未通过 3-无审核状态
   */
 private Boolean agentAuditStatus;
   /**
   * 代理商审核时间
   */
 private Integer agentAuditAt;
   /**
   * 审核备注。在审核不通过时使用
   */
 private String agentAuditRemak;
   /**
   * 代理商状态  0-未开通 1-正常 2-已禁用  3-已注销
   */
 private Boolean agentStatus;
   /**
   * 申请代理时间
   */
 private Integer agentApplyAt;
   /**
   * 代理商禁用原因
   */
 private String agentDisableRemark;
   /**
   * 代理商禁用时间
   */
 private Integer agentDisableAt;
   /**
   * 代理开始时间
   */
 private Long agentBeg;
   /**
   * 代理结束时间
   */
 private Long agentEnd;
   /**
   * 创建时间
   */
 private Integer createdAt;
   /**
   * 更新时间
   */
 private Integer updatedAt;
   /**
   * 创建者id，即该企业所属用户id
   */
 private Integer userId;
   /**
   * 企业状态  0-初始状态  1-试用状态  2-服务状态
   */
 private Boolean state;
   /**
   * 企业服务状态 1-仅注册 2-试用中 3-试用到期 4-服务中 5-服务到期
   */
 private Boolean serviceStatus;
   /**
   * 企业临时认证状态  1-未认证 2-认证中 3-已认证  4- 已失效  （根据status,business_license_end,和最新认证记录来做的一个综合判断）
   */
 private Boolean tempStatus;
   /**
   * 是否显示标记为公开成员的名片
   */
 private Boolean isPublicCardShow;
   /**
   * 是否显示加入红人榜的名片（不需要标记是否为公开成员），只有is_public_card_show为1时，才有效
   */
 private Boolean isPublicCardRedShow;
   /**
   * 余额
   */
 private BigDecimal balance;
   /**
   * 是否开通商城  0-否  1-是
   */
 private Boolean openMall;
   /**
   * 首字母
   */
 private String alphabet;
   /**
   * 是否开通短信服务  0-否  1-是
   */
 private Boolean openSms;
   /**
   * 累计购买的服务天数
   */
 private Integer serviceDays;
   /**
   * 是否开启区块链  0-否  1-是
   */
 private Boolean openBlockChain;
   /**
   * 区块链id
   */
 private String blockChainId;
   /**
   * 区块链私钥
   */
 private String blockChainPrivateKey;
   /**
   * 区块链公钥
   */
 private String blockChainPublicKey;
   /**
   * 区块链标识
   */
 private String blockChainRecordId;
   /**
   * 是否开通收银台  0-否  1-是
   */
 private Boolean openCashier;
   /**
   * 是否是集团.  1是集团 0不是
   */
 private Integer isGroup;
   /**
   * 通联设置账号
   */
 private String tlBizUserId;
   /**
   * 应用小程序方案模板编号
   */
 private Integer planId;
}
