package com.learn.shiro.encod;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.nio.charset.StandardCharsets;

public class EncodingRealm extends AuthorizingRealm {
    /**
     *  认证
     *   在认证时需要加密服务
     *   通常前端传来的密码为明文
     *   但数据库存储提供的密码为暗文
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password=getPassword(username);
        ByteSource salt=ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo ai =
                new SimpleAuthenticationInfo(username, password,salt, getName());
        return ai;
    }

    /**
     * 模拟数据库查询密码
     *  加密后的
     * @return
     */
    private String getPassword(String username) {
        return new SimpleHash("MD5","123456",username,1).toString();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }


}
