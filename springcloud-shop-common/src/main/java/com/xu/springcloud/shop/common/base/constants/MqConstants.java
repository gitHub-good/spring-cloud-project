package com.xu.springcloud.shop.common.base.constants;

import lombok.Getter;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/10/31 14:11
 * @Description:
 */
@Getter
public enum MqConstants {

    /*ActiveMQ*/
   DEFAULT_MAIL("default_messages_queue"),
   TOPIC_QUEUE("topic_message_queue"),

   /*RabbitMQ*/
    XLL_TOPIC_QUEUE("xll_topic_queue"),
    XLL_TOPIC_EXCHANGE("xll_topic_exchange"),

    XLL_FANOUT_QUEUE("xll_fanout_queue"),
    XLL_FANOUT_EXCHANG("xll_fanout_exchange"),

    XLL_DIRECT_QUEUE("xll_direct_queue"),
    XLL_DIRECT_EXCHANG("xll_direct_exchange"),

    /*RabbitMQ Routing Key 路由键*/
    XLL_TOPIC_ROUTING_KEY("xll.topic.*"),
    XLL_DIRECT_ROUTING_KEY("xll.direct.*"),

    QUEUE_END("");
    private String typeName;

    MqConstants(String typeName){
        this.typeName = typeName;
    }


    public static void main(String[] agrs){
        System.out.println(MqConstants.DEFAULT_MAIL.getTypeName());
    }
}
