package com.learn.chapter14.quota.inmemory;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author james mu
 * @date 2019/8/10 下午4:50
 */
@Slf4j
public class KeyBasedIntervalRegistry {
    /**
     *  <ip-访问次数>
     */
    private final Map<String, IntervalCount> hostCounts = new ConcurrentHashMap<>();
    /**
     * 间隔毫秒
     */
    private final long intervalDurationMs;
    /**
     * 生存毫秒
     */
    private final long ttlMs;
    /**
     *  白名单
     */
    private final Set<String> whiteList;
    /**
     * 黑名单
     */
    private final Set<String> blackList;

    /**
     *
     * @param intervalDurationMs
     * @param ttlMs
     * @param whiteList
     * @param blackList
     * @param name
     */
    public KeyBasedIntervalRegistry(long intervalDurationMs, long ttlMs, String whiteList, String blackList, String name) {
        this.intervalDurationMs = intervalDurationMs;
        this.ttlMs = ttlMs;
        this.whiteList = Sets.newHashSet(StringUtils.split(whiteList, ','));
        this.blackList = Sets.newHashSet(StringUtils.split(blackList, ','));

    }

    private void validate(String name) {
        if (ttlMs < intervalDurationMs) {
            log.warn("TTL for {} IntervalRegistry [{}] smaller than interval duration [{}]", name, ttlMs, intervalDurationMs);
        }
        log.info("Start {} KeyBasedIntervalRegistry with whitelist {}", name, whiteList);
        log.info("Start {} KeyBasedIntervalRegistry with blacklist {}", name, blackList);
    }

    /**
     * 若IP在白名单内返回0
     * 若IP在黑名单内返回一个超出上限的值
     * @param clientHostId 主机IP
     * @return 当前周期内的累计访问次数
     */
    public long tick(String clientHostId) {
        IntervalCount intervalCount = hostCounts.computeIfAbsent(clientHostId, s -> new IntervalCount(intervalDurationMs));
        long currentCount = intervalCount.resetIfExpiredAndTick();
        System.out.println("来源:"+clientHostId);
        if (whiteList.contains(clientHostId)) {
            return 0;
        } else if (blackList.contains(clientHostId)) {
            return Long.MAX_VALUE;
        }
        return currentCount;
    }

    /**
     * 将该IP从内存中删除
     * 不再维护该IP的状态
     */
    public void clean() {
        hostCounts.entrySet().removeIf(entry -> entry.getValue().silenceDuration() > ttlMs);
    }

    public Map<String, Long> getContent() {
        return hostCounts.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry:: getKey,
                                interval -> interval.getValue().getCount()
                        )
                );
    }
}
