package com.xu.springcloud.shop.member.mq;

import com.alibaba.fastjson.JSONObject;
import com.xu.springcloud.shop.common.base.entity.MailBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/1 16:21
 * @Description:
 */
@Slf4j
@Service
public class RabbitMailTopicProducer {

//    @Qualifier("amqpTemplate")
    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * @date 2018/11/1 17:59
     * @Description: 发送邮件
     *          TopicExchange：交换机名称
     *          Route-Key：路由键
     *          要发送的内容
     */
    public void sendMail(MailBase mailBase) {
        log.info("《********** 使用RabbitMQ 发布订阅的方式 发送邮件报文信息 content:{}**********》", mailBase);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(mailBase);
        this.amqpTemplate.convertAndSend(mailBase.getExchangeName(),mailBase.getRoutingKey(), jsonObject);
    }
}
