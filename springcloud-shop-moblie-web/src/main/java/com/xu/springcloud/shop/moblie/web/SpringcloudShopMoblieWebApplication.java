package com.xu.springcloud.shop.moblie.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.xu.springcloud.shop.moblie.web.feign")
@SpringBootApplication
public class SpringcloudShopMoblieWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudShopMoblieWebApplication.class, args);
    }
}
