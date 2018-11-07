
package com.xu.springcloud.shop.moblie.web.controller;

import com.xu.springcloud.shop.common.base.constants.TokenConstants;
import com.xu.springcloud.shop.common.base.controller.BaseController;
import com.xu.springcloud.shop.common.base.utils.CookieUtil;
import com.xu.springcloud.shop.member.entity.MemberUser;
import com.xu.springcloud.shop.moblie.web.feign.UserLoginFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @classDesc: 功能描述:(登录控制器)
 */
@Slf4j
@Controller
public class LoginController extends BaseController {

	private static final String LOGIN = "login";

	private static final String INDEX = "index";

	private static final String ERROR = "common/error";

	@RequestMapping("/")
	public String index(){
		return INDEX;
	}
	@Autowired
	private UserLoginFeign userLoginFeign;

	@RequestMapping("/toLogin")
	public String toLogin() {
		return LOGIN;
	}
	@RequestMapping("/login")
	public String login(@ModelAttribute("memberUser")MemberUser memberUser, HttpServletResponse response) {
		Map<String, Object> mapToken = userLoginFeign.login(memberUser);
		mapToken = (Map<String, Object>)mapToken.get("data");
		if(mapToken == null){
			log.error("出现错误，原因：没有生成token值");
			return  ERROR;
		}
		String token = (String)mapToken.get("token");
		if(StringUtils.isEmpty(token) || token == null){
			log.error("出现错误，原因：没有生成token值");
			return ERROR;
		}
		log.info("拿到token登陆成功， token:",token);
		CookieUtil.addCookie(response, TokenConstants.USER_TOKEN_NAME.getTokenName(), token,  TokenConstants.WEB_USER_COOKIE_TOKEN_SURVIVE_TIME.getWebTokenTime());
		return INDEX;
	}

	/**
	 *
	 * @methodDesc: 功能描述:(生成qq授权)
	 * @param: @param
	 *             request
	 * @param: @return
	 * @param: @throws
	 *             QQConnectException
	 */
	private static final String AUTH_URI = "https://auth.xll.com:8306/auth-service/toAuthorize?response_type=code&client_id=101420900&redirect_uri=http://127.0.0.1:8100/xllLoginCallback";
	@RequestMapping("/toAuth")
	public String toAuth(){
		return "redirect:/"+AUTH_URI;
	}
	@RequestMapping("/xllLoginCallback")
	public String xllLoginCallback(HttpServletRequest request){
		String openId = request.getParameter("openId");

		return "redirect:"+INDEX;
	}
//	@RequestMapping("/auth")
//	public String auth(HttpServletRequest request){
//		Cookie[] cookie = request.getCookies();
//		String token = "" ;
//		for(int i=0 ;i<cookie.length;i++){
//			if(cookie[i].getName().equals("token")){
//				token = cookie[i].getValue();
//				break;
//			}
//		}
//		if(StringUtils.isEmpty(token) && token == null){
//			return "";
//		}
//		Map<String, Object> map = userLoginFeign.getUser(token);
//		MemberUser memberUser = (MemberUser) map.get(BaseConstants.HTTP_DATA.getConstantName());
//		return "";
//	}


//	@RequestMapping("/authUrl")
//	public String authorizeUrl(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
//		String authorizeUrl = new Oauth().getAuthorizeURL(request);
//		return "redirect:" + authorizeUrl;
//	}
//
//	@RequestMapping("/qqLoginCallback")
//	public String qqLoginCallback(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws QQConnectException {
//		// String code = request.getParameter("code");
//		// 第一步获取授权码
//		// 第二步获取accesstoken
//		AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
//		String accessToken = accessTokenObj.getAccessToken();
//		if (StringUtils.isEmpty(accessToken)) {
//			return setError(request, "QQ授权失败!", ERROR);
//		}
//
//		OpenID openidObj = new OpenID(accessToken);
//		// 数据查找openid是否关联,如果没有关联先跳转到关联账号页面,如果直接登录.
//		String userOpenId = openidObj.getUserOpenID();
//		Map<String, Object> userLoginOpenId = userFeign.userLoginOpenId(userOpenId);
//		Integer code = (Integer) userLoginOpenId.get(BaseApiConstants.HTTP_CODE_NAME);
//		if (code.equals(BaseApiConstants.HTTP_200_CODE)) {
//			String token = (String) userLoginOpenId.get("data");
//			CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
//			return "redirect:/" + INDEX;
//		}
//
//		// 没有关联QQ账号
//		httpSession.setAttribute(Constants.USER_SESSION_OPENID,userOpenId);
//		return ASSOCIATEDACCOUNT;
//
//	}

}
