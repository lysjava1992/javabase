package com.test.securtiy.component.sms;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤拦截
 * 提取认证
 */
public class SmsLoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 要拦截的路径
     */
    private static final String SMS_LOGIN_URL = "/sms/login";

    private static final String SMS_LOGIN_PHONE = "phone";
    private static final String SMS_LOGIN_CODE = "code";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(SMS_LOGIN_URL,
            "POST");
    private boolean postOnly = true;

    public SmsLoginAuthenticationProcessingFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
            if(postOnly&&!"POST".equals(request.getMethod())){
                throw  new AuthenticationServiceException("认证方式不支持");
            }

        String phone = obtainPhone(request);
        phone = (phone != null) ? phone : "";

        String code = obtainCode(request);
        code = (code != null) ? code : "";

        SmsLoginAuthenticationToken authRequest = new  SmsLoginAuthenticationToken(phone, code);

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
    @Nullable
    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(SMS_LOGIN_PHONE).trim();
    }
    @Nullable
    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(SMS_LOGIN_CODE).trim();
    }

    protected void setDetails(HttpServletRequest request, SmsLoginAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
