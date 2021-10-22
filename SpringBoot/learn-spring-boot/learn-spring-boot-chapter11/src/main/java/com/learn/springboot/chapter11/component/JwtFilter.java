package com.learn.springboot.chapter11.component;

import com.google.gson.Gson;
import com.learn.springboot.chapter11.model.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @description: 拦截 解析验证 jwt令牌
 * 取出用户的权限信息
 * 放回
 * @author: Mr.Luan
 * @create: 2020-04-02 16:15
 **/
public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        //解析令牌
        String jwtToken = request.getHeader("authorization");
        if (jwtToken == null) {
            Result result = new Result(401, "请先登录", "/login(POST)");
            servletResponse.setContentType("application/json;charset=utf-8");
            out(servletResponse, result);
            return;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey("jzhkr@2020")
                    .parseClaimsJws(jwtToken.replace("Bearer", ""))
                    .getBody();
            //封装权限并放回上下文
            String username = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.
                    commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
        } catch (ExpiredJwtException e) {
            Result result = new Result(401, "令牌已过期,请先登录", "/login(POST)");
            servletResponse.setContentType("application/json;charset=utf-8");
            out(servletResponse,result);
            return;
        }

        //继续执行流程
        filterChain.doFilter(request, servletResponse);
    }

    private void out(ServletResponse servletResponse, Result result) throws IOException {
        PrintWriter pw = servletResponse.getWriter();
        Gson gson = new Gson();
        pw.write(gson.toJson(result));
        pw.flush();
        pw.close();
    }
}
