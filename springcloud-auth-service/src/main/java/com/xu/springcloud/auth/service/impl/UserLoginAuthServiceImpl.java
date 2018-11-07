package com.xu.springcloud.auth.service.impl;

import com.xu.springcloud.auth.dao.AuthDao;
import com.xu.springcloud.auth.entity.UserAuth;
import com.xu.springcloud.auth.service.UserLoginAuthService;
import com.xu.springcloud.shop.common.base.utils.DateUtils;
import com.xu.springcloud.shop.common.base.utils.SerialNoUtil;
import com.xu.springcloud.shop.common.base.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/4 15:57
 * @Description:
 */
@Controller
public class UserLoginAuthServiceImpl implements UserLoginAuthService {

    @Autowired
    private AuthDao authDao;
    private static final String AUTH = "auth";

    public String toAuthorize(@RequestParam("response_type") String response_type,
                              @RequestParam("client_id")String client_id, @RequestParam("redirect_uri")String redirect_uri, HttpSession session){
        session.setAttribute("response_type",response_type);
        session.setAttribute("client_id",client_id);
        session.setAttribute("redirect_uri",redirect_uri);

        return AUTH;
    }
    @RequestMapping("/toAuth")
    public String toAuth(HttpSession session){
        String response_type = (String) session.getAttribute("response_type");
        String client_id = (String)session.getAttribute("client_id");
        String redirect_uri = (String)session.getAttribute("redirect_uri");
        if(response_type.equals("code")){
            UserAuth userAuth = new UserAuth();
            String openId = TokenUtils.getToken();
            String authId = SerialNoUtil.getTabPkSn("tbl_auth_user",16);
            userAuth.setCreated(DateUtils.getTimestamp());
            userAuth.setUpdated(DateUtils.getTimestamp());
            userAuth.setOpenId(openId);
            userAuth.setId(authId);
            authDao.save(userAuth,"tbl_auth_user");
            return "redirect:"+(redirect_uri+"&openId="+openId);
        }
        return "";
    }
}
