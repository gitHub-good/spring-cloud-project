package com.xu.springcloud.auth.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @Auther: 徐亮亮
 * @Date: 2018/11/4 15:42
 * @Description:
 */
@RequestMapping("/auth-service")
public interface UserLoginAuthService {
    @GetMapping("/toAuthorize")
    String toAuthorize(@RequestParam("response_type") String response_type,
                       @RequestParam("client_id")String client_id, @RequestParam("redirect_uri")String redirect_uri, HttpSession session);
}
