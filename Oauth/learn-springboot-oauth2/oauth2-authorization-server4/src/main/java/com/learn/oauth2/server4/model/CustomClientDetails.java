package com.learn.oauth2.server4.model;

import com.sun.org.apache.xml.internal.resolver.readers.TR9401CatalogReader;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * @description:
 *      自定义oauth2的认证 客户端模型
 * @author: Mr.Luan
 * @create: 2020-04-08 08:47
 **/
@Data
public class CustomClientDetails implements ClientDetails{
    private String clientId;
    private String clientSecret;
    private String authorizedGrantTypes;
    private String redirectUris;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String resourceIds;
    private String scopes;
    private String roles;

    /**
     * 客户端ID
     * @return
     */
    @Override
    public String getClientId() {
        return this.clientId;
    }

    /**
     * 客户端  可访问资源范围
     * @return
     */
    @Override
    public Set<String> getResourceIds() {
        String[] arr=this.resourceIds.split(";");

        return new HashSet<>(Arrays.asList(arr));
    }

    /**
     * 是否需要秘钥验证
     * @return
     */
    @Override
    public boolean isSecretRequired() {
        return this.clientSecret==null;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.scopes==null;
    }

    /**
     * 可用资源范围
     * @return
     */
    @Override
    public Set<String> getScope() {
        String[] arr=this.scopes.split(";");

        return new HashSet<>(Arrays.asList(arr));
    }

    /**
     * 允许的认证模式
     * @return
     */
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        String[] arr=this.authorizedGrantTypes.split(";");

        return new HashSet<>(Arrays.asList(arr));
    }

    /**
     * 授权码模式下的授权页面
     * @return
     */
    @Override
    public Set<String> getRegisteredRedirectUri() {
        String[] arr=this.redirectUris.split(";");

        return new HashSet<>(Arrays.asList(arr));
    }

    /**
     * 客户端 角色 权限信息
     * @return
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
      List<GrantedAuthority>list=new ArrayList<>();
        String[] arr=this.roles.split(";");
        for (String role:arr) {
            list.add(new SimpleGrantedAuthority(role));
        }
      return list;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValidity;
    }

    /**
     *
     * @param s
     * @return
     */
    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    /**
     * 附加信息
     * @return
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
