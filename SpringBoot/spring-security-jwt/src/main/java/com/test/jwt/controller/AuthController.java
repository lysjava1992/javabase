package com.test.jwt.controller;

import com.test.jwt.component.JwtTokenUtil;
import com.test.jwt.model.CustomUser;
import com.test.jwt.model.JwtRequest;
import com.test.jwt.model.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Qualifier("customUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @PostMapping("login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest){
      // 检查
        check(jwtRequest);
      // 认证
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }catch (UsernameNotFoundException |BadCredentialsException ignored){
            return new JwtResponse(false,"用户名或密码错误");
        }catch (DisabledException |LockedException |AccountExpiredException e){
            return new JwtResponse(false,"账户异常");
        }catch (CredentialsExpiredException e){
            return new JwtResponse(false,"凭证过期");
        }catch (Exception e){
            return new JwtResponse(false,e.getMessage());
        }
      // 生成token
        CustomUser user= (CustomUser) userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtil.generateToken(user);
     return new JwtResponse(true,"SUCCESS",token);
    }

    private void check(JwtRequest jwtRequest) {
        if(jwtRequest.getPassword()==null){
            jwtRequest.setPassword("");
        }
        if(jwtRequest.getUsername()==null){
            jwtRequest.setUsername("");
        }
    }
}
