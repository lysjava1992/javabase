package com.learn.springboot.chapter10.component;

import com.learn.springboot.chapter10.domain.VerifyCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-31 11:43
 **/
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {
        private String defaultFilterUrl="/doLogin";
        private String defaultMethod="POST";
        @Autowired
         private SecurityFailedHandler failedHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            if(defaultMethod.equalsIgnoreCase(request.getMethod())
                    &&defaultFilterUrl.equals(request.getServletPath())){
                    String code=request.getParameter("code");
                if(code==null||code.equals("")){
                    failedHandler.onAuthenticationFailure(request,response,new VerifyCodeException("验证码不能为空"));
                    return;
                }
                    String sessionCode= (String) request.getSession().getAttribute("code");
                if(!(code.toLowerCase().equals(sessionCode.toLowerCase()))){
                    failedHandler.onAuthenticationFailure(request,response,new VerifyCodeException("验证码错误"));
                    return;
                }
            }
                filterChain.doFilter(request,response);


    }

}
