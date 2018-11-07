package com.xu.springcloud.shop.common.base.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MailBase implements Serializable {

    //邮件接收人
    private String recipient;
    //邮件主题
    private String subject;
    //邮件内容
    private String content;
    //发送对象
    private String sendToUser;
    //邮件账户值
    private String mailAccountValue;
    //消息队列名称
    private String queueName;
    //交换机名称
    private String exchangeName;
    //路由键
    private String routingKey;
}
