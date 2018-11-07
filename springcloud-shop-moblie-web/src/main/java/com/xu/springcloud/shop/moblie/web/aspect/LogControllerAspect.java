package com.xu.springcloud.shop.moblie.web.aspect;

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
public class LogControllerAspect {
    private JSONObject jsonObject = new JSONObject();

    String pk ;
    // 申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.xu.springcloud.shop.moblie.web.controller.*.*(..))")
    private void controllerAspect() {
    }

    // 请求method前打印内容
    @Before(value = "controllerAspect()")
    public void requestBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("《********** 亲！！！前端用户请求的基本信息请查阅 啦啦啦 Start **********》");
        try {
            // 打印请求内容
            log.info("请求地址:" + request.getRequestURL().toString());
            log.info("请求方式:" + request.getMethod());
            log.info("请求类方法:" + joinPoint.getSignature());
            Object[] requestData = joinPoint.getArgs();
            StringBuffer json = new StringBuffer();
            for(int i=0; i<requestData.length-1; i++) {
                json.append(JSONObject.toJSONString(requestData[i]));
            }
//            String json = JSONObject.toJSONString(joinPoint.getArgs());
            log.info("请求类方法参数:" + json);/*Arrays.toString(joinPoint.getArgs())*/
        } catch (Exception e) {
            log.error("《********** LogControllerAspect.class requestBefore 错误！！！ **********》", e);
        }
        log.info("《********** 亲！！！前端用户请求的基本信息请查阅 啦啦啦 End  **********》");
    }

    // 在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void responseAfterReturn(Object o) {
        log.info("《********** 亲！！！后端响应返回的数据信息请查阅 啦啦啦 Start **********》");
        try {
            log.info("Response内容:" + jsonObject.toJSONString(o));
        } catch (Exception e) {
            log.error("《********** LogControllerAspect.class responseAfterReturn() 错误！！！ **********》", e);
        }
        log.info("《********** 亲！！！后端响应返回的数据信息请查阅 啦啦啦 End **********》");
    }
}
