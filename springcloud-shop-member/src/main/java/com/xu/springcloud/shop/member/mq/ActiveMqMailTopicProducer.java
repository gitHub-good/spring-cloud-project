//package com.xu.springcloud.shop.member.mq;
//
//import com.xu.springcloud.shop.common.base.constants.MqConstants;
//import com.xu.springcloud.shop.member.utils.MailMsgTemplate;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.activemq.command.ActiveMQTopic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.jms.Destination;
//
///**
// * @Auther: 徐亮亮
// * @Date: 2018/11/1 01:20
// * @Description:  activeMq的发布订阅模式
// */
//@Slf4j
//@Service
//public class ActiveMqMailTopicProducer {
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    //消息队列名称
////    @Value("${messages.queue}")
////    private String MESSAGES_QUEUE;
//
//    /**
//     * @date 2018/10/31 20:33
//     * @Description: 消息服务发送者
//     */
//    public void sendMessage(Object object) {
////        if(MESSAGES_QUEUE == null){
////            log.info("《********** sendMessage() Topic发布订阅的没有消息队列**********》");
////            return;
////        }
//        // 队列 发布订阅模式
//        Destination activeMQTopic = new ActiveMQTopic(MqConstants.DEFAULT_MAIL.getTypeName());
//        // 组装报文格式
//        String mailMessage = MailMsgTemplate.mailMessage(object);
//        log.info("《********** sendMessage() 注册发送邮件报文信息 mailMessage:{}**********》", mailMessage);
//        jmsMessagingTemplate.convertAndSend(activeMQTopic, mailMessage);
//    }
//    /**
//     * @date 2018/10/31 19:03
//     * @Description: 自定义 消息服务发送者
//     * Destination:为发送的消息队列
//     * json：发送消息的json数据
//     */
//    public void sendMessage(String json) {
//        // 队列 发布订阅模式
//        Destination activeMQTopic = new ActiveMQTopic(MqConstants.TOPIC_QUEUE.getTypeName());
//        log.info("《********** sendMessage() 注册发送邮件报文 mailMessage:{}**********》", json);
//        jmsMessagingTemplate.convertAndSend(activeMQTopic, json);
//    }
//}
