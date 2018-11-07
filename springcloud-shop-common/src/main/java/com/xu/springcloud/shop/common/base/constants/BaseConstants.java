package com.xu.springcloud.shop.common.base.constants;

import lombok.Getter;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/10/31 13:22
 * @Description: 基础通用的常量枚举类
 */
@Getter
public enum BaseConstants {
    /*状态码常量值*/
    HTTP_200_CODE("HTTP_200_CODE", 200),
    HTTP_400_CODE("HTTP_400_CODE", 400),
    HTTP_500_CODE("HTTP_500_CODE",500),

    /*状态说明字符串常量*/
    HTTP_SUCCESS("success"),
    HTTP_FAIL("fail"),
    HTTP_CODE("code"),
    HTTP_DATA("data"),
    HTTP_MESSAGE("msg");


    private String constantName;
    private Integer constantValue;
    BaseConstants(String constantName, Integer constantValue){
        this.constantName = constantName;
        this.constantValue = constantValue;
    }

    BaseConstants(Integer constantValue){
        this.constantValue = constantValue;
    }
    BaseConstants(String constantName){
        this.constantName = constantName;
    }

    public static void main(String[] args){
        System.out.println(BaseConstants.HTTP_200_CODE+"="+BaseConstants.HTTP_200_CODE.getConstantValue());
        String s = BaseConstants.HTTP_SUCCESS.toString();
        System.out.println(s);
    }
}
