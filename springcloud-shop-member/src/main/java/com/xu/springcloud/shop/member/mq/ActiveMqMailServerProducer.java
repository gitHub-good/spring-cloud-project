//package com.xu.springcloud.shop.member.mq;
//
//import com.xu.springcloud.shop.common.base.constants.MqConstants;
//import com.xu.springcloud.shop.common.base.entity.MailBase;
//import com.xu.springcloud.shop.member.entity.UserEntity;
//import com.xu.springcloud.shop.member.utils.MailMsgTemplate;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.activemq.command.ActiveMQQueue;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.jms.Destination;
//
///**
// * @Auther: 徐亮亮
// * @Date: 2018/11/1 18:33
// * @Description:  邮件的提供者服务类(ActiveMQ)
// */
//@Slf4j
//@Service
//public class ActiveMqMailServerProducer {
//
//    @Autowired
//    private ActiveMqRegisterMailOneToOneProducer registerMailOneToOneProducer;
//
//    @Autowired
//    private ActiveMqMailTopicProducer mailTopicProducer;
//
//
//    /**
//     * @date 2018/11/1 1:28
//     * @Description: ActiveMQ点对点发送消息
//     */
//    public  void sendActiveMqOneToOneMail(UserEntity userEntity){
//        if(userEntity.getEmail().isEmpty() || userEntity.getEmail().equals(null)){
//            log.error("《************** 没有发现email账户，无法找到发送消息的对象 MailServerProducer.class sendActiveMqOneToOneMail() **************》");
//            return;
//        }
//        // 队列
//        String MESSAGES_QUEUE = MqConstants.DEFAULT_MAIL.getTypeName();
//        // 队列
//        Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
//        // 组装报文格式
//        String mailMessage = MailMsgTemplate.mailMessage(userEntity.getEmail(), userEntity.getUserName());
//        //注册成功后发送邮件
//        registerMailOneToOneProducer.sendMessage(activeMQQueue, mailMessage);
//    }
//    /**
//     * @date 2018/11/1 1:37
//     * @Description: ActiveMQ发布订阅
//     */
//    public void sendActiveMqTopicMail(UserEntity userEntity){
//        if(userEntity.getEmail().isEmpty() || userEntity.getEmail().equals(null)){
//            log.error("《************** 没有发现email账户，无法找到发送消息的对象 MailServerProducer.class sendActiveMqTopicMail() **************》");
//            return;
//        }
//        // 组装报文格式
//        String mailMessage = MailMsgTemplate.mailMessage(userEntity.getEmail(), userEntity.getUserName());
//        //注册成功后发送邮件
//        mailTopicProducer.sendMessage(mailMessage);
//    }
//}
