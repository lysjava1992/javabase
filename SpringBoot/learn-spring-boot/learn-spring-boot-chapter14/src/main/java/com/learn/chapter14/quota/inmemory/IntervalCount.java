package com.learn.chapter14.quota.inmemory;


import com.learn.chapter14.quota.Clock;

import java.util.concurrent.atomic.LongAdder;

/**
 *  周期更新
 * 用于单个IP的访问统计
 *
 */
public class IntervalCount {

    private final LongAdder addr = new LongAdder();
    /**
     * 一个周期间隔
     */
    private final long intervalDurationMs;
    /**
     * 开始时间
     */
    private volatile long startTime;

    /**
     * 最高一次访问时间
     */
    private volatile long lastTickTime;

    /**
     *
     * @param intervalDurationMs 周期 毫秒
     */
    public IntervalCount(long intervalDurationMs) {
        this.intervalDurationMs = intervalDurationMs;
        startTime = Clock.millis();
    }

    /**
     *  获取当前周期内的访问次数
     *   1. 判断周期是否已过
     *   2. 重置
     *   3. 访问+1
     *
     * @return 返回该周期内的访问此时
     */
    public long resetIfExpiredAndTick(){
        if (isExpired()){
            reset();
        }
        tick();
        return addr.sum();
    }

    /**
     *  当前时间-最后一次访问时间
     * @return
     */
    public long silenceDuration() {
        return Clock.millis() - lastTickTime;
    }

    public long getCount() {
        return addr.sum();
    }

    /**
     * 访问+1
     *
     */
    private void tick() {
        addr.add(1);
        lastTickTime = Clock.millis();
    }

    /**
     * 开始新周期
     */
    private void reset() {
        addr.reset();
        lastTickTime = Clock.millis();
        startTime = Clock.millis();
    }

    /**
     * 判断是否开启新周期
     * @return
     */
    private boolean isExpired() {
        return (Clock.millis() - startTime) > intervalDurationMs;
    }

}
