
package com.simple.actor.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 记录调用次数
 * 传递结果
 * 状态
 */
@Data
@AllArgsConstructor
public class ActorTestCtx {

    private volatile CountDownLatch latch;
    private final AtomicInteger invocationCount;
    /**
     * 调用次数
     */
    private final int expectedInvocationCount;
    private final AtomicLong actual;

    public void clear() {
        latch = new CountDownLatch(1);
        invocationCount.set(0);
        actual.set(0L);
    }
}
