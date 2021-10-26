package com.learn.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个完整的Realm包含两部分
 * 1.认证
 * 2.授权
 */
public class CustomAuthorizingRealm2 extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username= (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        Set<String> pSet=new HashSet<>();
        pSet.add("p2");
        pSet.add("person:*");
        Set<String> rSet=new HashSet<>();
        rSet.add("r2");
        if("hangman".equals(username)){
             authorizationInfo.setStringPermissions(pSet);
             authorizationInfo.setRoles(rSet);
        }
        return authorizationInfo;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       String username= (String) token.getPrincipal();
       String password= new String((char[]) token.getCredentials()) ;
        if(!"hangman".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"123456".equals(password)){
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
