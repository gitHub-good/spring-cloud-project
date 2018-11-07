package com.xu.springcloud.shop.message;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 徐亮亮
 * @date 2018/11/1 0:47
 * @Description: 消息推送分发服务
 */
@EnableDiscoveryClient
@EnableEurekaClient
@EnableRabbit
@SpringBootApplication
public class SpringcloudShopMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudShopMessageApplication.class, args);
    }
}
