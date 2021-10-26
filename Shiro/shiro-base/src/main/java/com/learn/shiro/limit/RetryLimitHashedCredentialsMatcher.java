package com.learn.shiro.limit;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;


/**
 * 实现次数限制的时机不止一处
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Map<String, RetryLimit> countMap = new ConcurrentHashMap();
    /**
     * 周期线程定时清空 解锁
     */
    ScheduledExecutorService executor;

    public RetryLimitHashedCredentialsMatcher() {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::cyclicCheck, 0, 5, TimeUnit.SECONDS);
    }


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        RetryLimit current = countMap.getOrDefault(username, new RetryLimit(5, 1000 * 5));
        if (current.isLock()) {
            throw new ExcessiveAttemptsException();
        }
        boolean isOk = super.doCredentialsMatch(token, info);
        current.tryAgain();
        if (isOk) {
            countMap.remove(username);
        } else {
            countMap.put(username, current);
        }
        return isOk;
    }

    /**
     * 周期监测
     */
    private void cyclicCheck() {
        System.out.println("-------重置---");
        Set<Map.Entry<String, RetryLimit>> entries = countMap.entrySet();
        for (Map.Entry<String, RetryLimit> entry : entries) {
            RetryLimit rl = entry.getValue();
            if (rl.isClear()) {

                countMap.remove(entry.getKey());
            }
        }
    }
    public void  Stop(){
        executor.shutdown();
    }
}
