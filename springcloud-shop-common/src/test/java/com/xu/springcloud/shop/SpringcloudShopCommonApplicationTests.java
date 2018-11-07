package com.xu.springcloud.shop;

import com.xu.springcloud.shop.common.base.constants.TokenConstants;
import com.xu.springcloud.shop.common.base.service.BaseStringRedisService;
import com.xu.springcloud.shop.common.base.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringcloudShopCommonApplicationTests {
    @Autowired(required = false)
    BaseStringRedisService baseStringRedisService;

    @Test
    public void contextLoads() {
        // 生成对应的token
        String token = TokenUtils.getToken();
        // key为自定义令牌,用户的userId作为value 存放在redis中
        Long timeOut = TokenConstants.USER_TOKEN_SURVIVE_TIME.getTokenSurviveTime();
        baseStringRedisService.setString(token,"XLL-MU6436655152",timeOut);
        log.info("测试成功！！");

    }

}
