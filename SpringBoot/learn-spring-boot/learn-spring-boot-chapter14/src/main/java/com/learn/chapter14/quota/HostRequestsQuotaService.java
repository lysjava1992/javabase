package com.learn.chapter14.quota;

import com.learn.chapter14.quota.QuotaService;
import com.learn.chapter14.quota.RequestLimitPolicy;
import com.learn.chapter14.quota.host.HostIntervalRegistryCleaner;
import com.learn.chapter14.quota.host.HostIntervalRegistryLogger;
import com.learn.chapter14.quota.host.HostRequestIntervalRegistry;
import com.learn.chapter14.quota.host.HostRequestLimitPolicy;
import com.learn.chapter14.quota.inmemory.IntervalRegistryCleaner;
import com.learn.chapter14.quota.inmemory.IntervalRegistryLogger;
import com.learn.chapter14.quota.inmemory.KeyBasedIntervalRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author james mu
 * @date 2019/8/12 上午9:13
 */
@Service
@Slf4j
public class HostRequestsQuotaService implements QuotaService {

    /**
     * 负责所有IP和访问次数的维护
     */
    private final KeyBasedIntervalRegistry requestRegistry;

    /**
     * 访问上限
     */
    private final RequestLimitPolicy requestsPolicy;

    /**
     *
     */
    private final IntervalRegistryCleaner registryCleaner;

    /**
     * 定时 日志输出
     */
    private final IntervalRegistryLogger registryLogger;
    /**
     * 是否开启
     */
    private final boolean enabled;

    public HostRequestsQuotaService(HostRequestIntervalRegistry requestRegistry, HostRequestLimitPolicy requestsPolicy,
                                    HostIntervalRegistryCleaner registryCleaner, HostIntervalRegistryLogger registryLogger,
                                    @Value("${quota.host.enabled}") boolean enabled) {
        this.requestRegistry = requestRegistry;
        this.requestsPolicy = requestsPolicy;
        this.registryCleaner = registryCleaner;
        this.registryLogger = registryLogger;
        this.enabled = enabled;
    }

    @PostConstruct
    public void init() {
        if (enabled) {
            registryCleaner.schedule();
            registryLogger.schedule();
        }
    }

    @PreDestroy
    public void close() {
        if (enabled) {
            registryCleaner.stop();
            registryLogger.stop();
        }
    }

    /**
     *
     * @param key 主机IP
     * @return
     */
    @Override
    public boolean isQuotaExceeded(String  key){
        if (enabled) {
            long count = requestRegistry.tick(key);
            System.out.println("周期内访问次数: "+count);
            return !requestsPolicy.isValid(count);
        }
        return false;
    }
}
