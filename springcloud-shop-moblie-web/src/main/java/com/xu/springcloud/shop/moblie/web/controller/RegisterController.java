
package com.xu.springcloud.shop.moblie.web.controller;

import com.xu.springcloud.shop.common.base.constants.BaseConstants;
import com.xu.springcloud.shop.common.base.controller.BaseController;
import com.xu.springcloud.shop.member.entity.MemberUser;
import com.xu.springcloud.shop.moblie.web.feign.UserLoginFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
public class RegisterController extends BaseController {

	// 跳转到注册
	private static final String REGISTER = "register";
	// 登录页面
	private static final String LOGIN = "login";
	@Autowired
	private UserLoginFeign userLoginFeign;

	@RequestMapping("/toRegister")
	public String toRegister() {
		return REGISTER;
	}

	@RequestMapping("/register")
	public String register(@ModelAttribute("memberUser") MemberUser memberUser, HttpServletRequest request) {
		try {
			Map<String, Object> registerResult = userLoginFeign.register(memberUser);
			log.info("《****** refMbUserService.register result》" + registerResult);
			Integer code = (Integer) registerResult.get("code");
			Integer httpResCode200 = BaseConstants.HTTP_200_CODE.getConstantValue();
			if (!code.equals(httpResCode200)) {
				String msg = (String) registerResult.get("msg");
				return setError(msg, request, REGISTER);
			}
			return "redirect:/" + LOGIN;
		} catch (Exception e) {
			log.error("《********* register() 发送错误 *****************》", e);
			return setError("注册失败!请稍后重试!", request, REGISTER);
		}

	}

	public String setError(String errorMsg, HttpServletRequest request, String registerAddress) {
		request.setAttribute("error", errorMsg);
		return registerAddress;
	}

	public static void main(String[] args) {
		Integer code = 200;
		Integer httpResCode200 = 200;
		System.out.println(code.equals(httpResCode200));

		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0;i<5;i++)
			stringBuffer.append(i);

		System.out.println(stringBuffer);
	}

}
