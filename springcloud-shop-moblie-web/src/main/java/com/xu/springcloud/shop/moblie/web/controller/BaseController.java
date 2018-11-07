
package com.xu.springcloud.shop.moblie.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xu.springcloud.shop.common.base.constants.BaseConstants;
import com.xu.springcloud.shop.member.entity.MemberUser;
import com.xu.springcloud.shop.moblie.web.feign.UserLoginFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 徐亮亮
 * @date 2018/11/2 18:37
 * @Description: 通用Controller前台控制器（与前端交互）
 */
@Controller
public class BaseController {
	@Autowired
	private UserLoginFeign userLoginFeign;

	public MemberUser getMemberUser(String token) {
		Map<String, Object> userMap = userLoginFeign.getUser(token);
		Integer code = (Integer) userMap.get(BaseConstants.HTTP_CODE.getConstantName());
		if (!code.equals(BaseConstants.HTTP_200_CODE.getConstantValue())) {
			return null;
		}
		// 获取data参数
		LinkedHashMap linkedHashMap = (LinkedHashMap) userMap.get(BaseConstants.HTTP_DATA.getConstantName());
		String json = new JSONObject().toJSONString(linkedHashMap);
		MemberUser memberUser = new JSONObject().parseObject(json, MemberUser.class);
		return memberUser;

	}

	public String setError(HttpServletRequest request, String msg, String addres) {
		request.setAttribute("error", msg);
		return addres;
	}

}
