package com.learn.shiro.limit;

/**
 *  登录次数的限制
 */
public class RetryLimit {
    /**
     * 当前次数
     */
    private int currentCount;
    /**
     * 锁定后等待时间
     */
    private long waitTime;
    /**
     * 是否锁定
     */
    private boolean isLock;
    /**
     * 最后一次登录时间
     */
    private long lastTime;
    /**
     * 最大次数
     */
    private int maxCount;


    public RetryLimit(int maxCount, long waitTime) {
        this.maxCount = maxCount;
        this.waitTime = waitTime;
        this.currentCount = 0;
    }

    /**
     * 验证次数+1
     */
    public void tryAgain() {
        this.lastTime = System.currentTimeMillis();
        this.currentCount++;
        if(this.currentCount>maxCount){
            this.isLock=true;
        }
    }


    public boolean isLock() {
        return this.isLock;
    }

    /**
     * 若当前时间-最后一次登录时间>锁定间隔
     *  已经锁定的可以删除解锁
     *  没有锁定的可以删除重置次数
     * @return
     */
    public boolean isClear() {
        return (System.currentTimeMillis()-lastTime)>waitTime;
    }
}
