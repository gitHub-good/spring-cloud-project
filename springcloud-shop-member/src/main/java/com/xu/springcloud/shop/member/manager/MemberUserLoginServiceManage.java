package com.xu.springcloud.shop.member.manager;

import com.xu.springcloud.shop.member.entity.MemberUser;

import java.util.Map;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/2 13:58
 * @Description: 业务逻辑管理层
 */
public interface MemberUserLoginServiceManage {
    /**
     * @date 2018/10/31 20:03
     * @Description: 用户注册
     */
    void register(MemberUser memberUser);

    /**
     * @date 2018/11/2 16:07
     * @Description: 用户登陆
     */
    Map<String, Object> login(MemberUser memberUser);

    /**
     * @date 2018/11/4 14:34
     * @Description: 根据token查询用户信息
     */
    Map<String, Object> getUser(String token);

    /**
     * @date 2018/11/4 14:34
     * @Description: 根据openId关联用户信息
     */
    Map<String, Object> userLoginOpenId(String openId);

}
