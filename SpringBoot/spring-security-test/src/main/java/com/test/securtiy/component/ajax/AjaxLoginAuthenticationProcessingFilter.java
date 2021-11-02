package com.test.securtiy.component.ajax;

import com.alibaba.fastjson.JSONObject;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AjaxLoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/ajax/login",
            "POST");
    private boolean postOnly = true;
    public AjaxLoginAuthenticationProcessingFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (postOnly&&!("POST").equals(request.getMethod())){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //   application/x-www-form-urlencoded; charset=UTF-8
        //   application/json;charset=UTF-8

        String format=request.getContentType();
        String username ="";
        String password = "";
        if(format.contains("json")){
            JSONObject json=getParameters(request);
             username = obtainUsername(json);
             password = obtainPassword(json);
        }else {
            username = obtainUsername(request)==null?username:obtainUsername(request);
            password = obtainPassword(request)==null?password:obtainPassword(request);
        }
        AjaxLoginAuthenticationToken token=new AjaxLoginAuthenticationToken(username,password);
        setDetails(request, token);
        return this.getAuthenticationManager().authenticate(token);
    }
    @Nullable
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
    }
    @Nullable
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
    }
    @Nullable
    protected String obtainUsername(JSONObject jsonObject) {
        if(jsonObject.containsKey(SPRING_SECURITY_FORM_USERNAME_KEY)){
            return (String) jsonObject.get(SPRING_SECURITY_FORM_USERNAME_KEY);
        }
        return "";
    }
    @Nullable
    protected String obtainPassword(JSONObject jsonObject) {
        if(jsonObject.containsKey(SPRING_SECURITY_FORM_PASSWORD_KEY)){
            return (String) jsonObject.get(SPRING_SECURITY_FORM_PASSWORD_KEY);
        }
        return "";
    }
    protected void setDetails(HttpServletRequest request, AjaxLoginAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public static JSONObject getParameters(HttpServletRequest request) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
        return jsonObject;
    }

}
