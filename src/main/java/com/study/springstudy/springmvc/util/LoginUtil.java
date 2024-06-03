package com.study.springstudy.springmvc.util;

import com.study.springstudy.springmvc.chap05.dto.response.LoginUserInfoDto;
import com.study.springstudy.springmvc.chap05.entity.Auth;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtil {

    public static final String LOGIN = "login";
    public static final String AUTO_LOGIN_COOKIE = "auto";

    // 로그인 여부 확인
    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute(LOGIN) != null;
    }

    // 로그인한 회원의 계정명 얻기
    public static String getLoggedInUserAccount(HttpSession session) {
        LoginUserInfoDto loggedInUser = getLoggedInUser(session);
        return loggedInUser != null ? loggedInUser.getAccount() : null;
    }

    // 로그인한 회원의 이름 얻기
    public static String getLoggedInUserName(HttpSession session) {
        LoginUserInfoDto loggedInUser = getLoggedInUser(session);
        return loggedInUser != null ? loggedInUser.getNickName() : null;
    }

    public static LoginUserInfoDto getLoggedInUser(HttpSession session) {
        return (LoginUserInfoDto) session.getAttribute(LOGIN);
    }

    public static boolean isAdmin(HttpSession session) {
        LoginUserInfoDto loggedInUser = getLoggedInUser(session);
        Auth auth = null;
        if (isLoggedIn(session)) {
            auth = Auth.valueOf(loggedInUser.getAuth());
        }
        return auth == Auth.ADMIN;
    }

    public static boolean isMine(String boardAccount, String loggedInUserAccount) {
        return boardAccount.equals(loggedInUserAccount);
    }

    public static boolean isAutoLogin(HttpServletRequest request) {
        Cookie autoLoginCookie = WebUtils.getCookie(request, AUTO_LOGIN_COOKIE);
        return autoLoginCookie != null;
    }
}
