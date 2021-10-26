package com.learn.shiro.realm;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.collections.ListUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.*;

public class CustomRealmTest {
    public static void main(String[] args) {
        DefaultSecurityManager securityManager=new DefaultSecurityManager();
        Collection<Realm> realms=new ArrayList<>();
        realms.add(new CustomRealmOne());
        realms.add(new CustomRealmTwo());
        realms.add(new CustomRealmThree());
//        securityManager.setRealms(realms);
//        securityManager.getRealms().forEach(a->System.out.println(a.getName()));

        ModularRealmAuthenticator authenticator=new ModularRealmAuthenticator();
        authenticator.setRealms(realms);
          authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        // authenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
      //   authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);
        securityManager.setRealms(realms);
        securityManager.getRealms().forEach(a->System.out.println(a.getName()));

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("hangman","123456");

        try {
            subject.login(token);
            System.out.println("登录成功");
             List<String> list=   subject.getPrincipals().asList();
             System.out.println(list.size());
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("登录失败");
        }
        subject.logout();
    }
}
