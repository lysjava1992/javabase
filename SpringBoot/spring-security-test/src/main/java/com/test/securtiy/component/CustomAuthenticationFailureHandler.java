package com.test.securtiy.component;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        String msg=e.getMessage();
        if(e instanceof UsernameNotFoundException ||e instanceof BadCredentialsException){
            msg= "用户名或密码错误";
        }else if(e instanceof DisabledException){
            msg= "用户被禁用";
        }else if(e instanceof CredentialsExpiredException){
            msg= "凭证过期";
        }else if(e instanceof AccountExpiredException){
            msg= "账户过期";
        }else if(e instanceof LockedException){
            msg= "账户被锁定";
        } else{
            e.printStackTrace();
        }
        request.getSession().setAttribute("err",msg);
        getRedirectStrategy().sendRedirect(request, response, "/login");
    }
}
