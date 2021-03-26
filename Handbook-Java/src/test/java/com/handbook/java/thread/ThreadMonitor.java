package com.handbook.java.thread;

/**
 * 每一个线程对应一个监视对象
 */
public class ThreadMonitor {
    private boolean isNotified=false;
    public synchronized void doWait() throws InterruptedException {
        while (!isNotified){
            this.wait();
        }
        this.isNotified=false;
    }
    public synchronized void doNotify(){
        this.isNotified=true;
        this.notify();
    }
}
