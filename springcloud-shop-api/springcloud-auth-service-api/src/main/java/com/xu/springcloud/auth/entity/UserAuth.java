package com.xu.springcloud.auth.entity;

import com.xu.springcloud.shop.common.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/4 16:04
 * @Description:
 */
@Setter
@Getter
public class UserAuth extends BaseEntity {
    //用户授权Id
    private String openId;
}
