package com.xu.springcloud.shop.member.api.service;

import com.xu.springcloud.shop.member.entity.MemberUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 
 * @classDesc: 功能描述:(会员服务接口)
 */
@RequestMapping("/shop-member")
public interface MemberUserLoginService {

	/**
	 * 
	 * @methodDesc: 功能描述:(注册接口)
	 * @param: @param
	 *             mbUser
	 * @param: @return
	 */
	@RequestMapping("/register")
	Map<String, Object> register(@RequestBody MemberUser memberUser);

	/**
	 * @date 2018/11/2 16:24
	 * @Description: 登录成功后,生成对应的token，作为key,将用户userID作为value存放在redis中.返回token给客户端.
	 */
	@PostMapping("/login")
	Map<String, Object> login(@RequestBody MemberUser memberUser);

	/**
	 * @date 2018/11/2 16:25
	 * @Description: 使用token查找用户信息
	 */
	@PostMapping("/getUser")
	Map<String, Object> getUser(@RequestParam("token") String token);

	/**
	 * @date 2018/11/4 14:31
	 * @Description: 使用openid关联用户信息进行登陆
	 */
	@PostMapping("/userLoginOpenId")
	Map<String, Object> userLoginOpenId(@RequestParam("openId") String openId);
}
