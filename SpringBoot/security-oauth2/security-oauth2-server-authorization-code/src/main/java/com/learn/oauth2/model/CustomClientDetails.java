package com.learn.oauth2.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Data
public class CustomClientDetails implements ClientDetails {
    /**
     * 客户端Id
     */
    private String clientId;
    /**
     * 客户端拥有的 资源ID
     *  相当于客户端的权限
     */
    private Set<String> resourceIds;

    /**
     *  客户端是否需要秘钥认证
     */
    private boolean secretRequired;
    /**
     *  秘钥
     */
    private String clientSecret;
    /**
     * 是否限定客户端
     */
   private boolean scoped;
    /**
     *  范围 比 resourceIds更细的权限控制
     */
   private Set<String> scope;
    /**
     * 允许的授权类型
     */
    private Set<String> authorizedGrantTypes;

    // authorization_code模式下的重定向登录地址
    private  Set<String> registeredRedirectUri;

    /**
     * 客户端自身的权限
     */
   private Collection<GrantedAuthority> authorities;
    /**
     * 令牌有效期
     *  秒
     */
   private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌的token有效期
     */
    private Integer refreshTokenValiditySeconds;



    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }

    /**
     * 客户端附加信息
     * @return
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
