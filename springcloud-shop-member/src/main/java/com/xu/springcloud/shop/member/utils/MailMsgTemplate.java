package com.xu.springcloud.shop.member.utils;

import com.alibaba.fastjson.JSONObject;
import com.xu.springcloud.shop.common.base.constants.MqConstants;
import com.xu.springcloud.shop.member.entity.MemberUser;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/10/31 20:19
 * @Description: 发送邮件的模板工具类
 */
public final class MailMsgTemplate {

    /**
     * @date 2018/10/31 21:10
     * @Description: 《对象》 邮件发送模板，使用json格式
     */
    public static String mailMessage(Object obj) {
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("queueName", MqConstants.DEFAULT_MAIL.getTypeName());
        root.put("header", header);
        root.put("content", obj);
        return root.toJSONString();
    }
    /**
     * @date 2018/10/31 20:07
     * @Description: 《默认》 邮件发送模板，使用json格式
     */
    public static String mailMessage(String email, String userName) {
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("queueName", MqConstants.TOPIC_QUEUE.getTypeName());
        JSONObject content = new JSONObject();
        content.put("mail", email);
        content.put("userName", userName);
        root.put("header", header);
        root.put("content", content);
        return root.toJSONString();
    }
    public static void main(String[] agrs){
        MemberUser userEntity = new MemberUser();
        userEntity.setEmail("132346");
        userEntity.setPassword("132464");
        userEntity.setUserName("行数");
        System.out.println(MailMsgTemplate.mailMessage(userEntity));
    }
}
