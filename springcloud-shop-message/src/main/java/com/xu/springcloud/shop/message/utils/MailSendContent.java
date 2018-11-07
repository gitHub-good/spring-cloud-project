package com.xu.springcloud.shop.message.utils;

import com.xu.springcloud.shop.common.base.entity.MailBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/1 19:21
 * @Description: 邮件的发送内容主题等配置
 */
@Slf4j
public class MailSendContent {
    /**
     * @date 2018/11/1 1:49
     * @Description: 抽取的公共发送信息方法
     */
    public static SimpleMailMessage setContent(SimpleMailMessage message, MailBase mailBase){
        message.setSubject("微服务邮件异步推送消息");/*邮件主题*/
        message.setSentDate(new Date());/*邮件发送时间*/
        String content = "测试测试，收到扣我，你的回复可以让一个程序猿知道自己的服务是否运行成功！！！！谢谢啦，亲";
        message.setText(content);/*邮件内容*/
        log.info(" 发送短信邮箱 mail:{} ", mailBase.getMailAccountValue());
        return message;
    }
}
