package com.handbook.java.datastructure.queue;

public class CircularQueue {
    private int[] queue;
    private int head;
    private int tail;
    private int size;
    private int count;
    public int getCount() {
        return count;
    }
    public CircularQueue(int size) {
        this.size = size;
        this.queue=new int[size];
        this.head=0;
        this.tail=0;
        this.count=0;
    }
    public boolean enqueue(int n){
         if(count==size){
            System.out.print("队列已满");
            return false;
          }
          queue[tail]=n;
          if(tail==(size-1)){
              tail=0;
          }else {
              tail++;
          }
        count++;
        return true;
    }
    public int dequeue(){
        if(count==0){
            System.out.print("队列为空");
            return -1;
        }
        int n=queue[head];
        if(head==(size-1)){
            head=0;
        }else {
            head++;
        }
        count--;
        return n;
    }
}
