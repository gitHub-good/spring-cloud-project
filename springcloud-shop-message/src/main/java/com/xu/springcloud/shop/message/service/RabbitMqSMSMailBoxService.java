package com.xu.springcloud.shop.message.service;

import com.alibaba.fastjson.JSONObject;
import com.xu.springcloud.shop.common.base.entity.MailBase;
import com.xu.springcloud.shop.message.utils.MailSendContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/1 19:09
 * @Description:
 */
@Slf4j
@Component
public class RabbitMqSMSMailBoxService implements MessageAdapter{
    @Autowired
    private JavaMailSender mailSender; // 自动注入的Bean，邮件发送
    @Value("${spring.mail.username}")
    private String sender; // 读取配置文件中的参数

    @Override
    public void distributeOneToOneMessage(JSONObject jsonObject) {

    }

    @Override
    public void distributeTopic(MailBase mailBase) {
        // 开始发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);/*发送者*/
        String[] to = {mailBase.getMailAccountValue(),sender};
        message.setTo(to);/*接收者*/
        mailSender.send(MailSendContent.setContent(message,mailBase));
    }
}
