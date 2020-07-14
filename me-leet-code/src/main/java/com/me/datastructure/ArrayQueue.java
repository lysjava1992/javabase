package com.me.datastructure;

/**
 * @description:
 * 队列
 *   基于数组
 *   先进先出
 * @author: Mr.Luan
 * @create: 2020-05-06 08:51
 **/
public class ArrayQueue {
    /**
     * 存储空间
     */
    private String[] items;
    /**
     * 头
     */
    private int head;
    /**
     * 尾
     */
    private int tail;
    /**
     * 计数
     */
    private int count;
    /**
     * 默认容量
     */
    private int capacity=6;
    public ArrayQueue() {
         this.items=new String[capacity];
         this.head=this.tail=0;
         this.count=0;

    }
    public ArrayQueue(int capacity) {
        this.items=new String[capacity];
        this.head=this.tail=0;
        this.count=0;
    }
    /**
     * 入队
     * @param str
     */
    public boolean inQueue(String  str){
        if(count<capacity){

            if(tail<capacity){
                //有空位 直接放
                items[tail++]=str;
                count++;
            }else {
                //有空位 在前面 需要搬移
              int k=0;
              for (int i=head;i<tail;i++){
                  items[k++]=items[i];
               }
                head=0;
                tail=k;
                items[tail++]=str;
                count++;
            }
            return true;
        }
        return false;
    }
    /**
     * 出队
     * @param
     * @return
     */
    public String outQueue(){
           if(count>0){
               count--;
               return items[head++];
           }
        return null;
    }
    public static void main(String[] args) {
           ArrayQueue arrayQueue=new ArrayQueue();
        for (int i = 0; i <6 ; i++) {
            arrayQueue.inQueue(i+"");
            System.out.println("入栈:  "+i);
        }
        for (int i = 0; i <3 ; i++) {
         String str=arrayQueue.outQueue();
         if(str==null){break;}
            System.out.println("出站:  "+str);
        }
    }
}
