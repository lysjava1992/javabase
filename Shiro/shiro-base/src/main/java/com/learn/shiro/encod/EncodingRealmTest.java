package com.learn.shiro.encod;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.SimpleByteSource;


public class EncodingRealmTest {
    public static void main(String[] args) {

        // 密码匹配器 必须与加密设置相匹配
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashIterations(1);
        matcher.setHashAlgorithmName("MD5");


        EncodingRealm realm=new EncodingRealm();
        realm.setCredentialsMatcher(matcher);

        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("hangman","123456");
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            System.out.println("登录失败");
        }
    }
}
