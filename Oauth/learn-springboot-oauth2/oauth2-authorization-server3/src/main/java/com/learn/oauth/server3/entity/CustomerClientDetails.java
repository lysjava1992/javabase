package com.learn.oauth.server3.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import java.util.*;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-07 13:53
 **/
@Data
public class CustomerClientDetails implements ClientDetails {
      private String clientId;
      private String clientSecret;
      private String authorizedGrantTypes;
      private String redirectUri;
      private Integer accessTokenValidity;
      private Integer refreshTokenValidity;
      private String resourceIds;
      private String scopes;
      private String role;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
      String[] arr=this.resourceIds.split(";");
        return new HashSet<>(Arrays.asList(arr));
    }

    @Override
    public boolean isSecretRequired() {
        return  this.clientSecret != null;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.scopes != null && !this.scopes.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        String[] arr=this.scopes.split(";");
        return new HashSet<>(Arrays.asList(arr));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        String[] arr=this.authorizedGrantTypes.split(";");
        return new HashSet<>(Arrays.asList(arr));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        String[] arr=this.redirectUri.split(";");
        return new HashSet<>(Arrays.asList(arr));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        GrantedAuthority authority1=new SimpleGrantedAuthority(this.role);
        List<GrantedAuthority> list=new ArrayList<>();
        list.add(authority1);
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
     * 不清楚
     * @param s
     * @return
     */
    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
