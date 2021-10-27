package com.shiro.web.component;

import com.shiro.web.model.User;
import com.shiro.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
         String username= (String) principals.getPrimaryPrincipal();
        User user=userService.findByName(username);
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.addRole(user.getRole());
        authorizationInfo.setStringPermissions(user.getPermissions());
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            String username= (String) token.getPrincipal();
            User user=userService.findByName(username);
            if(user==null){
                throw  new UnknownAccountException();
            }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
