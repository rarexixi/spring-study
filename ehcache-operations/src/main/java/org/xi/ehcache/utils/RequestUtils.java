package org.xi.ehcache.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    public static String getToken(HttpServletRequest request) {
        String token;
        if (StringUtils.isNotBlank(token = request.getParameter("token"))) {
            return token;
        }
        Cookie tokenCookie = RequestUtils.getCookie(request, "Authorization");
        return tokenCookie == null ? null : tokenCookie.getValue();
    }
}
