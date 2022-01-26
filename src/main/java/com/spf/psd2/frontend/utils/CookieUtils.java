package com.spf.psd2.frontend.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class CookieUtils {

    private JwtConfig jwtConfig;

    public CookieUtils(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public void addAuthenticationCookie(HttpServletResponse response, String value){
        this.addCookie(response, jwtConfig.getHeader(), value);
    }

    public void addCookie(HttpServletResponse response, String name, String value){
        response.addCookie(new Cookie(name, value));
    }

    public void deleteAuthenticationCookie(HttpServletResponse response){
        this.deleteCookie(response, jwtConfig.getHeader());
    }

    public void deleteCookie(HttpServletResponse response, String name){
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

/*    public String getCookie(HttpServletResponse response, String name, String value){
        return response.addCookie(new Cookie(name, value));
    }*/

    public String getAuthorizationCookie(HttpServletRequest req) {
        return this.getCookieValue(req, jwtConfig.getHeader());
    }

    public String getCookieValue(HttpServletRequest req, String cookieName) {

        return req.getCookies() != null ? Arrays.stream(req.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null) : null;
    }
}
