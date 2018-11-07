package com.xu.springcloud.shop.common.base.utils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 徐亮亮
 * @date 2018/11/2 18:24
 * @Description: Cookie工具类
 */
public class CookieUtil {

    private CookieUtil() {
    }

    /**
     * @date 2018/11/2 18:23
     * @Description: 添加cookie
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }
    /**
     * @date 2018/11/2 18:24
     * @Description: 删除cookie
     */
    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie uid = new Cookie(name, null);
        uid.setPath("/");
        uid.setMaxAge(0);
        response.addCookie(uid);
    }

    /**
     * @date 2018/11/2 18:24
     * @Description: 获取cookie值
     */
    public static String getCookie(HttpServletRequest request,String cookieName) {
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}