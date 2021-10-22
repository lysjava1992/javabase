package com.learn.chapter14.quota.host;


import com.learn.chapter14.quota.inmemory.KeyBasedIntervalRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author james mu
 * @date 2019/8/12 上午9:56
 * 初始化配置
 */
@Component
@Slf4j
public class HostRequestIntervalRegistry extends KeyBasedIntervalRegistry {

    public HostRequestIntervalRegistry(@Value("${quota.host.intervalMs}") long intervalDurationMs,
                                       @Value("${quota.host.ttlMs}") long ttlMs,
                                       @Value("${quota.host.whitelist}") String whiteList,
                                       @Value("${quota.host.blacklist}") String blackList) {
        super(intervalDurationMs, ttlMs, whiteList, blackList, "host");
    }
}
