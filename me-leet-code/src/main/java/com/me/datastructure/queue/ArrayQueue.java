package com.me.datastructure.queue;

/**
 * 基于数组的队列
 *
 * @ClassName ArrayQueue
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/5/10 10:45
 * @Version 1.0
 **/
public class ArrayQueue {

    /**
     * 基于数据 线性队列
     */
    static class LinearQueue{
        // 容器
        private String[] items;
        //容量
        private int capacity=6;
        //计数
        private int count;
        //头 出队列
        private int head;
        //尾 进队列
        private int tail;

        public LinearQueue() {
          this.items=new String[this.capacity];
          this.count=0;
          this.head=0;
          this.tail=0;
        }

        public LinearQueue(int capacity) {
            this.capacity = capacity;
            this.items=new String[this.capacity];
            this.count=0;
            this.head=0;
            this.tail=0;
        }

        /**
         * 获取队列容量
         * @return
         */
        public int Size(){
            return this.capacity;
        }

        /**
         * 获取队列当前已用容量
         * @return
         */
        public int Count(){
            return this.count;
        }

        /**
         * 出栈
         * head +1 指向下一个
         * count -1
         * @return
         */
        public String dequeue(){
            if(count>0){
                count--;
                return items[head++];
            }else {
                return null;
            }
        }

        /**
         * 入栈
         * @param item
         * @return
         */
         public boolean enqueue(String item) {
             if(count>=capacity){
                 //栈满
                 return false;
             }else {
                 if(tail>=capacity){
                     //前移
                     int index=0;
                     for (int i = head; i <tail ; i++) {
                         items[index++]=items[head++];
                     }
                     head=0;
                     tail=count-1;
                 }
                     items[tail++]=item;
                     count++;
                 return true;
             }
         }

    }

    /**
     * 基于数据  循环队列
     */
    static class CycleQueue{
        // 容器
        private String[] items;
        //容量
        private int capacity=6;
        //计数
        private int count;
        //头 出队列
        private int head;
        //尾 进队列
        private int tail;

        public CycleQueue() {
            this.items=new String[this.capacity];
            this.count=0;
            this.head=0;
            this.tail=0;
        }

        public CycleQueue(int capacity) {
            this.capacity = capacity;
            this.items=new String[this.capacity];
            this.count=0;
            this.head=0;
            this.tail=0;
        }

        /**
         * 获取队列容量
         * @return
         */
        public int Size(){
            return this.capacity;
        }

        /**
         * 获取队列当前已用容量
         * @return
         */
        public int Count(){
            return this.count;
        }

        /**
         * 出栈
         * head +1 指向下一个
         * count -1
         * @return
         */
        public String dequeue(){
            if(count>0){

                count--;
                if(head>=capacity){
                    head=head-capacity;
                }
                return items[head++];
            }else {
                return null;
            }
        }

        /**
         * 入栈
         * @param item
         * @return
         */
        public boolean enqueue(String item) {
            if(count>=capacity){
                //栈满
                return false;
            }else {
                if(tail>=capacity){
                    //前移
                    tail=tail-capacity;
                }
                items[tail++]=item;
                count++;
                return true;
            }
        }

    }
    public static void main(String[] args) throws InterruptedException {
          int capacity=6;
          LinearQueue linearQueue=new LinearQueue(capacity);
        for (int i = 0; i <capacity ; i++) {
            linearQueue.enqueue(i+"");
        }
        while (true){
            System.out.println("出队："+linearQueue.dequeue());
            linearQueue.enqueue(capacity+"");
            System.out.println("入队： "+capacity);
            capacity++;

            Thread.sleep(1000);
        }
    }
}
