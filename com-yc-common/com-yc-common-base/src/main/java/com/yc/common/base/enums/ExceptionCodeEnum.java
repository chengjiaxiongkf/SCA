/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：ErrorCodeEnum.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */
package com.yc.common.base.enums;


import lombok.Getter;

/**
 * @Author: ChengJiaXiong
 * @Description: 公用的异常编码枚举
 * @Date: Created in 15:07 2021/2/8
 * @Params:  * @param null
 */
@Getter
public enum ExceptionCodeEnum {
	//找不到服务
	HTTP404(404,"找不到服务"),
	//无效的用户ID
	USER_ID_INVALID(001,"无效的用户ID"),
	;

	private final int code;
	private final String msg;
	ExceptionCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static ExceptionCodeEnum getEnum(int code) {
		for (ExceptionCodeEnum ele : ExceptionCodeEnum.values()) {
			if (ele.getCode() == code) {
				return ele;
			}
		}
		return null;
	}
}
