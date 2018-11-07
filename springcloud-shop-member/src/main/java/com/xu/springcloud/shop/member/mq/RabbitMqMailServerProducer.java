package com.xu.springcloud.shop.member.mq;

import com.xu.springcloud.shop.common.base.constants.MqConstants;
import com.xu.springcloud.shop.common.base.entity.MailBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/2 20:18
 * @Description: 邮件的提供者服务类(RabbitMQ)
 */
@Slf4j
@Service(value = "rabbitMq_Mail_Server" )
public class RabbitMqMailServerProducer {
//    @Qualifier("rabbitMailTopicProducer")
    @Autowired
    private RabbitMailTopicProducer rabbitMailTopicProducer;

    public void sendRabbitMqMailTopic(String email, String userName){
        if(StringUtils.isEmpty(email)){
            log.error("《************** 没有发现email账户，无法找到发送消息的对象 MailServerProducer.class sendRabbitMqMailTopic() **************》");
            return;
        }
        MailBase mailBase = new MailBase();
        mailBase.setQueueName(MqConstants.XLL_TOPIC_QUEUE.getTypeName());
        mailBase.setExchangeName(MqConstants.XLL_TOPIC_EXCHANGE.getTypeName());
        mailBase.setRoutingKey(MqConstants.XLL_TOPIC_ROUTING_KEY.getTypeName());
        mailBase.setSendToUser(userName);
        mailBase.setMailAccountValue(email);
        rabbitMailTopicProducer.sendMail(mailBase);
    }
}
