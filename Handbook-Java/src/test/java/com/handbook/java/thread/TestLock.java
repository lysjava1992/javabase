package com.handbook.java.thread;

public class TestLock {
    private boolean isLock=false;
    private Thread lockThread=null;
    public synchronized  void  lock() throws InterruptedException {
        //防止假唤醒
        while (isLock){
            wait();
        }
        isLock=true;
        lockThread=Thread.currentThread();
    }
    public synchronized void unlock(){
          if(lockThread!=Thread.currentThread()){
              throw new IllegalMonitorStateException("Calling thread has not locked this lock");
          }
          notify();
          isLock=false;
          lockThread=null;
    }
}
