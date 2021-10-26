package com.learn.shiro.limit;

import com.learn.shiro.encod.EncodingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;


public class RetryLimtTest {
    public static void main(String[] args) throws InterruptedException {

        // 密码匹配器 必须与加密设置相匹配
        HashedCredentialsMatcher matcher=new RetryLimitHashedCredentialsMatcher();
        matcher.setHashIterations(1);
        matcher.setHashAlgorithmName("MD5");


        EncodingRealm realm=new EncodingRealm();
        realm.setCredentialsMatcher(matcher);

        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("hangman","123456");
        for (int i = 0; i < 10; i++) {
            try {
                if(i>6){
                    System.out.println("--------"+i);
                    Thread.sleep(5000);
                }
                subject.login(token);
               System.out.println(subject.getSession().getId());
            }catch (AuthenticationException e){
                e.printStackTrace();
                System.out.println("登录失败");
            }
        }


    }
}
