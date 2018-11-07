//package com.xu.springcloud.shop.member.mq;
//
//import com.xu.springcloud.shop.common.base.constants.MqConstants;
//import com.xu.springcloud.shop.member.utils.MailMsgTemplate;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.jms.Destination;
//
///**
// * @author 徐亮亮
// * @date 2018/10/31 19:03
// * @Description: 使用MQ调用消息服务,推送到消息服务中去（生成消息内容信息）
// */
//@Slf4j
//@Service
//public class ActiveMqRegisterMailOneToOneProducer {
//
//	@Autowired
//	private JmsMessagingTemplate jmsMessagingTemplate;
//
//	//消息队列名称
//	@Value("${messages.queue}")
//	private String MESSAGES_QUEUE;
//
//	/**
//	 * @date 2018/10/31 20:33
//	 * @Description: 默认消息服务发送者 点对点模式
//	 */
//	public void sendMessage(Object object) {
//		if(MESSAGES_QUEUE == null){
//			MESSAGES_QUEUE = MqConstants.DEFAULT_MAIL.getTypeName();
//		}
//		// 队列 点对点模式
//		Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
//		// 组装报文格式
//		String mailMessage = MailMsgTemplate.mailMessage(object);
//		log.info("《********** regist() 注册发送邮件报文mailMessage:{}**********》", mailMessage);
//		jmsMessagingTemplate.convertAndSend(activeMQQueue, mailMessage);
//	}
//	/**
//	 * @date 2018/10/31 19:03
//	 * @Description: 自定义 消息服务发送者
//	 * Destination:为发送的消息队列
//	 * json：发送消息的json数据
//	 */
//	public void sendMessage(Destination destination, String json) {
//		log.info("《********** regist() 注册发送邮件报文mailMessage:{}**********》", json);
//		jmsMessagingTemplate.convertAndSend(destination, json);
//	}
//}
