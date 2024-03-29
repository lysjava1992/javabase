package com.learn.chapter14.quota.inmemory;

import com.google.common.collect.MinMaxPriorityQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author james mu
 * @date 2019/8/10 下午5:55
 */
@Slf4j
public abstract class IntervalRegistryLogger {

    private final int topSize;
    private final KeyBasedIntervalRegistry intervalRegistry;
    private final long logIntervalMin;
    private ScheduledExecutorService executor;

    public IntervalRegistryLogger(int topSize, long logIntervalMin, KeyBasedIntervalRegistry intervalRegistry) {
        this.topSize = topSize;
        this.logIntervalMin = logIntervalMin;
        this.intervalRegistry = intervalRegistry;
    }

    public void schedule() {
        if (executor != null) {
            throw new IllegalStateException("Registry Cleaner already scheduled");
        }
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::logStatistic, logIntervalMin, logIntervalMin, TimeUnit.MINUTES);
    }

    public void stop() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    public void logStatistic() {
        Map<String, Long> registryContent = intervalRegistry.getContent();
        int uniqHosts = registryContent.size();
        long requestsCount = registryContent.values().stream().mapToLong(i -> i).sum();
        Map<String, Long> top = getTopElements(registryContent);
        log(top, uniqHosts, requestsCount);

    }

    protected Map<String, Long> getTopElements(Map<String, Long> countMap) {
        MinMaxPriorityQueue<Map.Entry<String, Long>> topQueue = MinMaxPriorityQueue
                .orderedBy(Comparator.comparing((Function<Map.Entry<String, Long>, Long>) Map.Entry::getValue).reversed())
                .maximumSize(topSize)
                .create(countMap.entrySet());

        return topQueue.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    protected abstract void log(Map<String, Long> top, int uniqHosts, long requestsCount);
}
