package com.learn.chapter14.quota;

/**
 *  请求限制
 */
public abstract class RequestLimitPolicy {
    /**
     * 上限
     */
    private final long limit;

    public RequestLimitPolicy(long limit) {
        this.limit = limit;
    }

    /**
     *
     * @param currentValue 当前值和设置的上限比较
     *
     * @return 是否有效
     */
    public boolean isValid(long currentValue) {
        return currentValue <= limit;
    }
}
