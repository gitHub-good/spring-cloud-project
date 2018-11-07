package com.xu.springcloud.shop.member.api.service.impl;

import com.xu.springcloud.shop.common.base.service.BaseApiService;
import com.xu.springcloud.shop.common.base.utils.MD5Util;
import com.xu.springcloud.shop.member.api.service.MemberUserLoginService;
import com.xu.springcloud.shop.member.entity.MemberUser;
import com.xu.springcloud.shop.member.manager.MemberUserLoginServiceManage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 徐亮亮
 * @date 2018/10/31 22:06
 * @Description: 用户接口的真实操作处理 （作用：外部根据调用接口进行访问该服务则如同Controller控制器进行接收请求的数据进行访问服务然后进行响应数据返回给客户）
 */
@Slf4j
@RestController
public class MemberUserLoginServiceImpl extends BaseApiService implements MemberUserLoginService {

	@Autowired
	private MemberUserLoginServiceManage userServiceManage;

//	@Value("${messages.queue}")
//	private String MESSAGES_QUEUE;
	@Override
	public Map<String, Object> register(@RequestBody MemberUser memberUser) {
		if(StringUtils.isEmpty(memberUser.getUserName())){
			return this.setResultParameterError("用户名称不能为空!");
		}
		if(StringUtils.isEmpty(memberUser.getPassword())){
			return setResultParameterError("密码不能为空!");
		}
		try {
			String password = memberUser.getPassword();
			String phone = memberUser.getPhone();
			memberUser.setPassword(MD5Util.md5PassSalt(phone, password));
			userServiceManage.register(memberUser);
			return setResultSuccess();
		} catch (Exception e) {
			log.error("《******************* MemberUserServiceImpl.class register()出现错误 *********************》",e);
			return setResultError("注册失败!");
		}
	}

	@Override
	public Map<String, Object> login(@RequestBody MemberUser memberUser) {
		return userServiceManage.login(memberUser);
	}

	@Override
	public Map<String, Object> getUser(@RequestParam("token")String token) {
		if (StringUtils.isEmpty(token)) {
			return setResultParameterError("token不能为空!");
		}
		return userServiceManage.getUser(token);
	}

	@Override
	public Map<String, Object> userLoginOpenId(String openId) {
		return userServiceManage.userLoginOpenId(openId);
	}
}
