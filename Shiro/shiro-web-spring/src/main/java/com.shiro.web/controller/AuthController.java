package com.shiro.web.controller;

import com.shiro.web.model.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("auth")
public class AuthController {

    @PostMapping("login")
    @ResponseBody
    public Result login(String username,
                        String password,
                        boolean isRemember){
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            SecurityUtils.getSubject().login(token);
        }catch ( UnknownAccountException e){
            return new Result(false,"无效用户");
        }catch (CredentialsException e){
            return new Result(false,"认证异常");
        }catch ( AuthenticationException e){
            e.printStackTrace();
            return new Result(false,e.toString());
        }
        return new Result(true);
    }
    
    @GetMapping("logout")
    @ResponseBody
    public void login(){
        SecurityUtils.getSubject().logout();
    }
}
