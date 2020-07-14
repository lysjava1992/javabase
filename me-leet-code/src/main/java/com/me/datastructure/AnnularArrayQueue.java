package com.me.datastructure;

/**
 * @description:
 *   基于数组
 *    环形队列
 * @author: Mr.Luan
 * @create: 2020-05-06 11:55
 **/
public class AnnularArrayQueue {
    private String[] items;
    private int count;
    private int head;
    private int tail;
    private int capacity=6;
    public AnnularArrayQueue() {
        this.items=new String[this.capacity];
        this.head=0;
        this.tail=0;
        this.count=0;
    }
    public AnnularArrayQueue(int capacity) {
        this.capacity=capacity;
        this.items=new String[this.capacity];
        this.head=0;
        this.tail=0;
        this.count=0;
    }


    public boolean inQueue(String str){
        if(count<capacity){
            if(tail<capacity){
                items[tail++]=str;
            }
        }
        return false;
    }
    public static void main(String[] args) {

    }
}
