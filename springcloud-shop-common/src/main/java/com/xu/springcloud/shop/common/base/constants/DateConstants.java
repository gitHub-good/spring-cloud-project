package com.xu.springcloud.shop.common.base.constants;

import lombok.Getter;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/10/31 16:00
 * @Description: 日期和时间的常量枚举类
 */
@Getter
public enum DateConstants {
    /*默认的时间格式*/
    DATE_TO_TIME_DEFAULT_PATTERN("yyyy-MM-dd HH:mm:ss"),
    /*去除秒的时间格式*/
    DATE_TO_TIME_NOT_SECOND_PATTERN("yyyy-MM-dd HH:mm"),
    /** 默认的日期格式 年-月-日 显示格式 */
    DATE_DEFAULT_PATTERN("yyyy-MM-dd");

    private String pattern;
    DateConstants(String pattern){
        this.pattern = pattern;
    }
}
