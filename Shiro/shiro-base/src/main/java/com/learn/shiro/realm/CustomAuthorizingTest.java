package com.learn.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

public class CustomAuthorizingTest {
    public static void main(String[] args) {
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealms(Arrays.asList(new CustomAuthorizingRealm(),new CustomAuthorizingRealm2()));
        SecurityUtils.setSecurityManager(securityManager);
        AuthenticationToken token=new UsernamePasswordToken("hangman","123456");
        Subject subject=SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println(subject.hasRole("r1"));
            System.out.println(subject.hasRole("r2"));
            subject.checkPermission("user:delete:add");
            subject.checkPermission("person:delete:add");
        }catch (AuthenticationException e){
            System.out.println("登陆失败");
            e.printStackTrace();
        }
    }
}
