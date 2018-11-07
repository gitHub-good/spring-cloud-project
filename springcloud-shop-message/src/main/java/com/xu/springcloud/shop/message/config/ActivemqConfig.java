//package com.xu.springcloud.shop.message.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//
///**
// * @Auther: 徐亮亮
// * @Date: 2018/11/1 15:07
// * @Description:
// */
//@Configuration
//public class ActivemqConfig {
//
//    /**
//     * @date 2018/11/1 15:07
//     * @Description: msListener注解默认只接收queue消息,如果要接收topic消息,需要设置containerFactory
//     */
//    @Bean
//    public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory) {
//        DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();
//        topicListenerContainer.setPubSubDomain(true);
//        topicListenerContainer.setConnectionFactory(activeMQConnectionFactory);
//        return topicListenerContainer;
//    }
//}
