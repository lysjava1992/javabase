package com.shiro.web.component;

import net.sf.ehcache.Ehcache;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RetryLimitHashedCredentialsMatcher
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/10/28 21:24
 * @Version 1.0
 **/
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private static final String RETRY_LIMIT_KEY="passwordRetryCache";
    private static final int RETRY_LIMIT=5;
    private Cache<String, AtomicInteger> retryLimitCache;

    public void setCacheManager(CacheManager cacheManager) {
        this.retryLimitCache=cacheManager.getCache(RETRY_LIMIT_KEY);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
         String username= (String) token.getPrincipal();
         AtomicInteger count=   retryLimitCache.get(username);
         if(count==null){
             count=new AtomicInteger(0);
             retryLimitCache.put(username,count);
         }
         if(count.incrementAndGet()>RETRY_LIMIT){
          throw new ExcessiveAttemptsException();
         }
         boolean isOk=super.doCredentialsMatch(token, info);
         if(isOk){
             retryLimitCache.remove(username);
         }
        return isOk;
    }
}
