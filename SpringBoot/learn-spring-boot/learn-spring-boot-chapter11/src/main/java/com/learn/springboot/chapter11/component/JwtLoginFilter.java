package com.learn.springboot.chapter11.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.learn.springboot.chapter11.model.Customer;
import com.learn.springboot.chapter11.model.Result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;

/**
 * @description:  登录拦截 生成jwt令牌
 * @author: Mr.Luan
 * @create: 2020-04-02 16:15
 **/
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     *
     * @param defaultFilterProcessesUrl   拦截的url 即登录路径
     * @param authenticationManager  认证器
     */
    protected JwtLoginFilter(String defaultFilterProcessesUrl,
                             AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    /**
     *  从httpServletRequest提取用户名和密码
     *  封装为认证书
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
             Customer customer=  new ObjectMapper().readValue(request.getInputStream(), Customer.class);
             UsernamePasswordAuthenticationToken token=
                     new UsernamePasswordAuthenticationToken(customer.getUsername(),customer.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    /**
     * 登录成功 生成Jwt
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> collection=authResult.getAuthorities();
        StringBuffer roles=new StringBuffer("");
        for (GrantedAuthority ga:collection) {
            roles.append(ga.getAuthority())
                 .append(",");
        }
        String jwt= Jwts.builder()
                   .claim("authorities",roles)
                   .setSubject(authResult.getName())
                   .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                   .signWith(SignatureAlgorithm.HS512,"jzhkr@2020")
                   .compact();
        Result result=new Result(200,"登录成功",jwt);
        out(response,result);

    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
         failed.printStackTrace();
        String msg=failed.getMessage();
        if(failed instanceof UsernameNotFoundException ||failed instanceof BadCredentialsException){
            msg= "用户名或密码错误";
        }else if(failed instanceof DisabledException){
            msg= "用户被禁用";
        }else if(failed instanceof CredentialsExpiredException){
            msg= "凭证过期";
        }else if(failed instanceof AccountExpiredException){
            msg= "账户过期";
        }else if(failed instanceof LockedException){
            msg= "账户被锁定";
        }
        Result result=new Result(401,msg);
        out(response,result);
    }

    private void out(HttpServletResponse response, Result result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw=response.getWriter();
        Gson gson=new Gson();
        pw.write(gson.toJson(result));
        pw.flush();
        pw.close();
    }

}
