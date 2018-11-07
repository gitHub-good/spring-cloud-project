
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
 * @author 徐亮亮
 * @date 2018/10/31 23:30
 * @Description: 发送邮件业务逻辑
 */
@Component
@Slf4j
public class ActiveMqSMSMailboxService implements MessageAdapter {
	@Autowired
	private JavaMailSender mailSender; // 自动注入的Bean
	@Value("${spring.mail.username}")
	private String sender; // 读取配置文件中的参数

	/**
	 * @date 2018/11/1 0:49
	 * @Description: 点对点分发消息
	 */
	@Override
	public void distributeOneToOneMessage(JSONObject jsonObject) {
		MailBase mailBase = JSONObject.toJavaObject(jsonObject,MailBase.class);
		// 开始发送邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);//发送者
		message.setTo(mailBase.getMailAccountValue()); // 自己给自己发送邮件（接收者）
		mailSender.send(MailSendContent.setContent(message,mailBase));
	}
	
	/**
	 * @date 2018/11/1 1:18
	 * @Description:
	 */
	@Override
	public void distributeTopic(MailBase mailBase) {
		/*MailBase mailBase = JSONObject.toJavaObject(jsonObject,MailBase.class);
		// 开始发送邮件
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);*//*发送者*//*
		String[] to = {mailBase.getMailAccountValue(),sender};
		message.setTo(to);*//*接收者*//*
		mailSender.send(MailSendContent.setContent(message,mailBase));*/
	}

}