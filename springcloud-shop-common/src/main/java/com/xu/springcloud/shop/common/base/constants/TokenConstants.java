package com.xu.springcloud.shop.common.base.constants;

import lombok.Getter;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/2 16:50
 * @Description:
 */
@Getter
public enum TokenConstants {

    //用户会话保存90天
    USER_TOKEN_SURVIVE_TIME(60 * 60 * 24 * 90L),
    //web用户cookie保存时间1000*90天
    WEB_USER_COOKIE_TOKEN_SURVIVE_TIME(1000*60 * 60 * 24 * 90),

    USER_TOKEN_NAME("token");

    private int webTokenTime;
    private Long tokenSurviveTime;
    private String tokenName;

    TokenConstants(Long tokenSurviveTime){
        this.tokenSurviveTime = tokenSurviveTime;
    }
    TokenConstants(String tokenName){
        this.tokenName = tokenName;
    }
    TokenConstants(int webTokenTime){
        this.webTokenTime = webTokenTime;
    }
}
