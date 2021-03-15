package com.yc.user.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * @Author: ChengJiaXiong
 * @Description: 用户表
 * @Date: Created in 15:56 2021/2/7
 */
@Getter
@Setter
@TableName("user")
public class UserEntity implements Serializable {
    //后台用户id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //注册平台 0-企业 1-个人版app 2-代理商 3-有传后台 4-企业代理商合并 5-企业版app
    private Integer registPlatfrom;

    //注册设备 0 ios/1 android/2 小程序/3 h5/4 有传后台/5 企业后台/6 代理商后台/7 其他
    private Integer registDevice;

    //拼音串
    private String namePinyin;

    //姓名
    private String name;

    //昵称
    private String nickname;

    //性别 0-未知 1-男 2-女
    private Integer sex;

    //手机号码
    private String phone;

    //头像url
    private String headimg;

    //用户自己的邀请码
    private String inviteCode;

    //邀请人id
    private Long inviteUserId;

    //0没邀约/1企业管理员邀约/2加入/3拒绝
    private Integer inviteStatus;

    //密码哈希
    private String passwordHash;

    //苹果账号id
    private String appstoreId;

    //邮箱
    private String email;

    //微信号
    private String wxId;

    //微信小程序openId
    private String wxMiniOpenId;

    //微信公众号openId
    private String wxOpenId;

    //微信unionId
    private String wxUnionId;

    //微信公众号openid
    private String wxGzhOpenId;

    //是否实名认证 0未认证/1已认证
    private Boolean isAuth;

    //是否禁用 0-启用 1-禁用
    private Boolean isDisable;

    //禁用原因
    private String disableReason;

    //是否删除 0-正常 1-已删除
    private Boolean isDel;

    //创建时间
    private Integer createdAt;

    //更新时间
    private Integer updatedAt;

    //区块链id
    private String blockChainId;

    //区块链私钥
    private String blockChainPrivateKey;

    //区块链公钥
    private String blockChainPublicKey;
}
