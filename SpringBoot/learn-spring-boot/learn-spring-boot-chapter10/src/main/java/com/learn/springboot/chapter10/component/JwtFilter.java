package com.learn.springboot.chapter10.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 17:03
 **/
public class JwtFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        String jwtToken=request.getHeader("authorization");
        System.out.println(jwtToken);
        Claims claims= Jwts.parser().setSigningKey("private@tag")
                .parseClaimsJws(jwtToken.replace("Bearer",""))
                .getBody();
        String username = claims.getSubject();
        List<GrantedAuthority> authorities = AuthorityUtils.
                commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
        System.out.println(authorities.size()+"==============");
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request,servletResponse);
    }
}
