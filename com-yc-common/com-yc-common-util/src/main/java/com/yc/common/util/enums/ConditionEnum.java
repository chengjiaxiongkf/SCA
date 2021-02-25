package com.yc.common.util.enums;

/**
 * @author sangang
 */
public enum ConditionEnum {
    //
    EQ("等于="),
    NE("不等于<>"),
    GT("大于>"),
    GE("大于等于>="),
    LT("小于<"),
    LE("小于等于<="),
    LIKE("模糊查询 LIKE"),
    NOT_LIKE("模糊查询 NOT LIKE"),
    IN("IN 查询"),
    NOT_IN("NOT IN 查询"),
    IS_NULL("NULL 值查询"),
    IS_NOT_NULL("IS NOT NULL"),
    EXIST("EXISTS 条件语句"),
    NOT_EXIST("NOT EXISTS 条件语句"),
    FIND_IN_SET("FIND_IN_SET 条件语句"),
	MATCH("MATCH AGAINST 全文索引");
    //
    private String description;

    ConditionEnum(String description) {
        this.description = description;
    }
}
