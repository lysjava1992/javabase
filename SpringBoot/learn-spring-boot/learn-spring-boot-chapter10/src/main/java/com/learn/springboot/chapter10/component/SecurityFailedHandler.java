package com.learn.springboot.chapter10.component;

import com.learn.springboot.chapter10.domain.VerifyCodeException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-31 13:50
 **/
@Component
public class SecurityFailedHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        if(isAjaxRequest(request)){
            //返回json
            response.setStatus(500);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(e.getMessage());
            out.flush();
            out.close();
        }else{
            request.getSession().setAttribute("err",e.getMessage());
            getRedirectStrategy().sendRedirect(request, response, "/login");
        }

    }
    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header)){
            return true;
        }else{
            return false;
        }
    }
}
