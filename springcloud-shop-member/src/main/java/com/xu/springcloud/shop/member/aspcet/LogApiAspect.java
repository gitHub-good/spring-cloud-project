package com.xu.springcloud.shop.member.aspcet;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/10/31 22:27
 * @Description:
 */
@Slf4j
@Component
@Aspect
public class LogApiAspect {
    private JSONObject jsonObject = new JSONObject();

    String pk ;
    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.xu.springcloud.shop.member.manager.impl.*.*(..))")
    private void controllerAspect() {
    }

    // 请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("《********** 开始请求数据资源 **********》");
        try {
            // 打印请求内容
            log.info("请求地址:" + request.getRequestURL().toString());
            log.info("请求方式:" + request.getMethod());
            log.info("请求类方法:" + joinPoint.getSignature());
            String json = JSONObject.toJSONString(joinPoint.getArgs());
            log.info("请求类方法参数:" + json);/*Arrays.toString(joinPoint.getArgs())*/
        } catch (Exception e) {
            log.error("《********** LogAspectServiceApi.class methodBefore 错误！！！ **********》", e);
        }
        log.info("《********** 开始请求数据资源 **********》");
    }

    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o) {
        log.info("《********** 响应消息返回数据信息 **********》");
        try {
            log.info("Response内容:" + jsonObject.toJSONString(o));
        } catch (Exception e) {

            log.error("《********** LogAspectServiceApi.class methodAfterReturing() 错误！！！ **********》", e);
        }
        log.info("《********** 响应消息返回数据信息 **********》");
    }
}
