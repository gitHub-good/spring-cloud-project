package com.xu.springcloud.shop.member.config;

import com.xu.springcloud.shop.common.base.constants.MqConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/1 15:55
 * @Description: RabbitMq的发送消息的方式配置
 */
@Configuration
public class RabbitMqConfig {

    /**
     * @date 2018/11/1 15:56
     * @Description: Topic模式
     */
    @Bean
    public Queue topicQueue() {
        return new Queue(MqConstants.XLL_TOPIC_QUEUE.getTypeName());
    }

    /**
     * @date 2018/11/1 15:57
     * @Description: Topic交换机
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MqConstants.XLL_TOPIC_EXCHANGE.getTypeName());
    }

    /**
     * @date 2018/11/1 15:59
     * @Description: Topic消息绑定
     */
    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(MqConstants.XLL_TOPIC_ROUTING_KEY.getTypeName());
    }

    /**
     * @date 2018/11/1 16:00
     * @Description: Fanout模式
     * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
     */
    @Bean
    public Queue fanoutQueue() {
        return new Queue(MqConstants.XLL_FANOUT_QUEUE.getTypeName());
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(MqConstants.XLL_FANOUT_EXCHANG.getTypeName());
    }
    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    /**
     * @date 2018/11/1 16:02
     * @Description: direct模式
     * 消息中的路由键（routing key）如果和 Binding 中的 binding key 一致， 交换器就将消息发到对应的队列中。路由键与队列名完全匹配
     */
    @Bean
    public Queue directQueue() {
        return new Queue(MqConstants.XLL_DIRECT_QUEUE.getTypeName());
    }
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MqConstants.XLL_DIRECT_EXCHANG.getTypeName());
    }
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(MqConstants.XLL_DIRECT_ROUTING_KEY.getTypeName());
    }
    /**
     * @date 2018/11/1 18:54
     * @Description: 配置发送消息的格式为json
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate((connectionFactory));
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
