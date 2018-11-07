
package com.xu.springcloud.shop.moblie.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class DemoController  extends BaseController {
	// index页面
	public static final String INDEX = "index";

	@RequestMapping("/index")
	public String index(HttpServletRequest request,@RequestParam("token") String token) {
		log.info(" 我的web工程搭建成功啦!,userName:{}",getMemberUser(token));
		return INDEX;
	}
}
