package com.xu.springcloud.shop.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.xu.springcloud.shop")
public class SpringcloudShopMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudShopMemberApplication.class, args);
    }
}
