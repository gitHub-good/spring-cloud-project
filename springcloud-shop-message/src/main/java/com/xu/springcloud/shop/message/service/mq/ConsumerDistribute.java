package com.xu.springcloud.shop.message.service.mq;

import com.alibaba.fastjson.JSONObject;
import com.xu.springcloud.shop.common.base.constants.MqCTypeContant;
import com.xu.springcloud.shop.common.base.entity.MailBase;
import com.xu.springcloud.shop.message.service.ActiveMqSMSMailboxService;
import com.xu.springcloud.shop.message.service.MessageAdapter;
import com.xu.springcloud.shop.message.service.RabbitMqSMSMailBoxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 徐亮亮
 * @date 2018/10/31 23:31
 * @Description: 消息内容的分发，只要消息队列有消息内容则启动该项目就会监听到消息服务提供者的消息
 */
@Slf4j
@Component
public class ConsumerDistribute {

	@Autowired
	private ActiveMqSMSMailboxService smsMailboxService;
	@Autowired
	private RabbitMqSMSMailBoxService rabbitMqSMSMailBoxService;

	@RabbitListener(queues = {MqCTypeContant.XLL_TOPIC_QUEUE})
	public void distributeRabbitTopic(JSONObject json) {
		if (json == null) {
			log.info("《**************** mailBase的值为null ConsumerDistribute.class distributeRabbitTopic ******************》");
			return;
		}
//		JSONObject json = (JSONObject)JSONObject.toJSON(mailBase);
		MailBase mailBase = JSONObject.toJavaObject(json,MailBase.class);
		log.info(" 接受到消息内容json:{}: ", json);
		String queueName = mailBase.getQueueName();
		if (StringUtils.isEmpty(queueName)) {
			log.info("《********** 没有queueName 消息队列 ConsumerDistribute.class distributeRabbitTopic **********》");
			return;
		}
		MessageAdapter messageAdapter = null;
		/*可以配置多个不同的发送消息队列*/
		switch (queueName) {
			// 发送邮箱
			case MqCTypeContant.XLL_TOPIC_QUEUE:
				messageAdapter = rabbitMqSMSMailBoxService;
				// 执行消息服务
				messageAdapter.distributeTopic(mailBase);
				break;
			default:

				break;
		}
	}




	/**
	 * @methodDesc: 功能描述:(监听且接受生产者消息)
	 * @param: 报文json
	 */
//	@Scheduled(fixedDelay=3000)
/*	@JmsListener(destination = "default_messages_queue")
	public void distributeOneToOne(String json) {
		if (StringUtils.isEmpty(json)) {
			return;
		}
		log.info(" 接受到消息内容json:{}: ", json);
		JSONObject root = new JSONObject().parseObject(json);
		JSONObject header = root.getJSONObject("header");
		String queueName = header.getString("queueName");
		if (StringUtils.isEmpty(queueName)) {
			log.info("《********** 没有queueName 消息队列 **********》");
			return;
		}
		MessageAdapter messageAdapter = null;
		*//*可以配置多个不同的发送消息队列*//*
		switch (queueName) {
		// 发送邮箱
		case MqCTypeContant.SMS_MAIL:
			messageAdapter = smsMailboxService;
			// 执行消息服务
			JSONObject content = root.getJSONObject("content");
			messageAdapter.distributeOneToOneMessage(content);
			break;
		default:

			break;
		}
	}*/

//	@Scheduled(fixedDelay=3000)
	/*@JmsListener(destination = "topic_message_queue", containerFactory = "topicListenerContainer")
	public void distributeTopic(String json) {
		if (StringUtils.isEmpty(json)) {
			return;
		}
		log.info(" 接受到消息内容json:{}: ", json);
		JSONObject root = new JSONObject().parseObject(json);
		JSONObject header = root.getJSONObject("header");
		String queueName = header.getString("queueName");
		if (StringUtils.isEmpty(queueName)) {
			log.info("《********** 没有queueName 消息队列 **********》");
			return;
		}
		MessageAdapter messageAdapter = null;
		*//*可以配置多个不同的发送消息队列*//*
		switch (queueName) {
			// 发送邮箱
			case MqCTypeContant.SMS_MAIL:
				messageAdapter = smsMailboxService;
				// 执行消息服务
				JSONObject content = root.getJSONObject("content");
				messageAdapter.distributeTopic(content);
				break;
			case MqCTypeContant.TOPIC_QUEUE:
				messageAdapter = smsMailboxService;
				// 执行消息服务
				JSONObject contentTopic = root.getJSONObject("content");
				messageAdapter.distributeTopic(contentTopic);
			default:

				break;
		}
	}*/

}
