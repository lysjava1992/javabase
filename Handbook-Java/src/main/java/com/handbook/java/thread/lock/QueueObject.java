package com.handbook.java.thread.lock;

/**
 * 监听器对象
 */
public class QueueObject {
    private boolean isNotified=false;
    public synchronized void doWait() throws InterruptedException {
        /**
         *  使用 while()形式封装监听器的wait()和notify():
         *    1.方式wait()错过信号
         *    2.防止假唤醒
         *    3.多个线程等待相同的信号
         */
        while (!isNotified){
            this.wait();
        }
        this.isNotified=false;
    }
    public synchronized void doNotify(){
        this.isNotified=true;
        this.notify();
    }
    public boolean equals(Object o){
        return this == o;
    }
}
