package com.learn.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class CustomRealmOne implements Realm {
    @Override
    public String getName() {
        return "Custom_Realm_One";
    }

    /**
     * 判断是否支持
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 认证过程
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
            System.out.println("-------one-------");
              String username= (String) token.getPrincipal();
              String password=new String((char[]) token.getCredentials());
              if(!"hangman".equals(username)){
                  throw new UnknownAccountException();
              }
              if(!"123456".equals(password)){
                  throw new IncorrectCredentialsException();
              }

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
