package com.learn.springboot.chapter10.component;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-31 13:49
 **/

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 *   通过实现AuthenticationSuccessHandler
 *   来自定义登录功能
 * @author: Mr.Luan
 * @create: 2020-03-31 11:08
 **/
@Component
public class SecuritySuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("=====================成功");
       getRedirectStrategy().sendRedirect(request,response,"/index");

    }
}