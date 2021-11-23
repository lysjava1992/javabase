package com.handbook.java.thread.lock;

/**
 * 自定义锁
 */
public class CustomLock  {
    private boolean isLocked =false;
    private Thread lockingThread=null;

    /**
     * 加锁
     * isLocked true 说明有线程已经获取所，当前线程进入wait()
     *          false  线程可以直接执行，即拿到了锁{
     *                                              isLocked=true; 重置状态
     *                                              lockingThread=Thread.currentThread();
     *                                                 }
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException {
        // 自旋锁 防止假唤醒
        while (isLocked){
            wait();
        }
        isLocked=true;
        lockingThread=Thread.currentThread();
    }


    /**
     *  释放锁
     *   修改状态
     *   notify(),唤醒任一一个线程
     */
    public synchronized void unlock(){
        if(this.lockingThread !=Thread.currentThread()){
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLocked =false;
        lockingThread=null;
        notify();
    }
}
