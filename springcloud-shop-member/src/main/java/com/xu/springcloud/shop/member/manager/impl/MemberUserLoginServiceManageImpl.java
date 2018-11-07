package com.xu.springcloud.shop.member.manager.impl;

import com.xu.springcloud.shop.common.base.constants.ConstantsTables;
import com.xu.springcloud.shop.common.base.constants.TokenConstants;
import com.xu.springcloud.shop.common.base.service.BaseApiService;
import com.xu.springcloud.shop.common.base.service.BaseStringRedisService;
import com.xu.springcloud.shop.common.base.utils.DateUtils;
import com.xu.springcloud.shop.common.base.utils.MD5Util;
import com.xu.springcloud.shop.common.base.utils.SerialNoUtil;
import com.xu.springcloud.shop.common.base.utils.TokenUtils;
import com.xu.springcloud.shop.member.dao.MemberUserLoginDao;
import com.xu.springcloud.shop.member.entity.MemberUser;
import com.xu.springcloud.shop.member.manager.MemberUserLoginServiceManage;
import com.xu.springcloud.shop.member.mq.RabbitMqMailServerProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/2 13:59
 * @Description: 业务逻辑管理实现层
 */
@Slf4j
@Service(value = "memberUserServiceManageImpl")
public class MemberUserLoginServiceManageImpl extends BaseApiService implements MemberUserLoginServiceManage {

    @Autowired
    private MemberUserLoginDao memberUserLoginDao;
    @Qualifier(value = "rabbitMq_Mail_Server")
    @Autowired
    private RabbitMqMailServerProducer rabbitMqMailServerProducer;
    @Qualifier(value = "base_Redis")
    @Autowired
    private BaseStringRedisService baseStringRedisService;

    @Override
    public void register(MemberUser memberUser) {
        memberUser.setId(SerialNoUtil.getTabPkSn("tbl_member_user",16));
        memberUser.setCreated(DateUtils.getTimestamp());
        memberUser.setUpdated(DateUtils.getTimestamp());
        memberUserLoginDao.save(memberUser, ConstantsTables.TABLE_MB_USER.getTableName());

        //发送邮件消息(发消息推送到消息服务中间件去)
        rabbitMqMailServerProducer.sendRabbitMqMailTopic(memberUser.getEmail(), memberUser.getUserName());
    }


    @Override
    public Map<String, Object> login(MemberUser memberUser) {
        // 往数据进行查找数据
        String phone = memberUser.getPhone();
        String password = memberUser.getPassword();
        String newPassWord = MD5Util.md5PassSalt(phone, password);
        memberUser = memberUserLoginDao.getUserPhoneAndPwd(phone, newPassWord);
        if (memberUser == null) {
            log.info("*** MemberUserLoginServiceManageImpl.class login(MemberUser memberUser) ***");
            return setResultError(" MemberUserLoginServiceManageImpl.class login()生成token出错，原因：账号或密码错误");
        }
        String userId = memberUser.getId();
        // key为自定义令牌,用户的userId作为value 存放在redis中
        String token = setUserToken(userId);
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        return setResultSuccessData(tokenMap);

    }

    @Override
    public Map<String, Object> getUser(String token) {
        // 从redis中查找到userId
        String userId = baseStringRedisService.get(token);
        if (StringUtils.isEmpty(userId)) {
            log.info("*** MemberUserLoginServiceManageImpl.class getUser(String token) ***");
            return setResultError("用户已经过期!");
        }
        MemberUser userInfo = memberUserLoginDao.getUserInfo(userId);
//        userInfo.setPassword(null);
        return setResultSuccessData(userInfo);
    }

    /**
     * @date 2018/11/4 15:18
     * @Description: 外界服务关联用户信息进行登陆
     */
    @Override
    public Map<String, Object> userLoginOpenId(String openId) {
        MemberUser userEntity = memberUserLoginDao.findUserOpenId(openId);
        if (userEntity == null) {
            log.info("*** 没有关联用户请进行关联操作 MemberUserLoginServiceManageImpl.class userLoginOpenId(String openId) ***");
            return setResultError("没有关联用户");
        }
        // 生成对应的token
        String token = setUserToken(userEntity.getId());
        return setResultSuccessData(token);
    }

    /**
     * @date 2018/11/4 14:47
     * @Description: 生成token方法
     */
    private String setUserToken(String id) {
        // key为自定义令牌,用户的userId作作为value 存放在redis中
        String token = TokenUtils.getToken();
        Long timeOut = TokenConstants.USER_TOKEN_SURVIVE_TIME.getTokenSurviveTime();
        baseStringRedisService.setString(token, id, timeOut);
        return token;
    }
}
