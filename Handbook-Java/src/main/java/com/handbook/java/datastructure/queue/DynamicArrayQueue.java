package com.handbook.java.datastructure.queue;

public class DynamicArrayQueue {
    private int[] queue;
    private int head;
    private int tail;
    private int size;
    private int count;

    public int getCount() {
        return count;
    }



    public DynamicArrayQueue(int size) {
        this.size = size;
        this.queue=new int[size];
        this.head=0;
        this.tail=0;
        this.count=0;
    }
    public boolean enqueue(int n){
        if(tail==size&&count==size){
            System.out.print("队列已满");
            return false;
        }else if(tail==size&&count<size){
            for(int i=head;i<tail;i++){
                queue[i-head]=queue[i];
            }
            tail-=head;
            head=0;

        }
        queue[tail]=n;
        tail++;
        count++;
        return true;
    }
    public int dequeue(){
        if(head==tail){
            System.out.print("队列为空--");
            return -1;
        }
        int n=queue[head];
        head++;
        count--;
        return n;
    }
}
