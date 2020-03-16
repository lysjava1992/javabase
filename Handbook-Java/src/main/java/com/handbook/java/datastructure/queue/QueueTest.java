package com.handbook.java.datastructure.queue;

public class QueueTest {
    public static void main(String[] args) {
        //testArrayQueue();
        //testDynamicArrayQueue();
        LinkListQueue llq=new LinkListQueue();
        for(int i=0;i<10;i++){
            llq.enqueue(i);
        }
        for (int i=0;i<10;i++){
            System.out.print(llq.dequeue()+" ");
        }
    }

    /**
     * 定容数组 ：队列满员后出队可以再进队
     */
   public static void testDynamicArrayQueue(){
   // DynamicArrayQueue daq=new DynamicArrayQueue(10);
       CircularQueue daq=new CircularQueue(10);
       System.out.println("入队顺序：");
       for (int i = 1; i < 15; i++) {
           boolean result = daq.enqueue(i);
           if (!result)
               break;
           System.out.print(i + " ");
       }
       System.out.println("\n出队顺序：");
       for (int i = 1; i < 6; i++) {
           int result = daq.dequeue();
           if (result==-1)
               break;
           System.out.print(result + " ");
       }
       System.out.println("\n入队顺序：");
       for (int i = 11; i < 16; i++) {
           boolean result = daq.enqueue(i);
           if (!result)
               break;
           System.out.print(i + " ");
       }
       System.out.println("\n出队顺序：");
       for (int i = 1; i < 15; i++) {
           int result = daq.dequeue();
           if (result==-1)
               break;
           System.out.print(result + " ");
       }
   }
    /**
     * 定容数组 ：队列满员后即使全部出队也不能再进队
     */
    public static void testArrayQueue() {
        ArrayQueue aq = new ArrayQueue(10);
        System.out.println("入队顺序：");
        for (int i = 1; i < 15; i++) {
            boolean result = aq.enqueue(i);
            if (!result)
                break;
            System.out.print(i + " ");
        }
        System.out.println("\n出队顺序：");
        for (int i = 1; i < 15; i++) {
            int result = aq.dequeue();
            if (result==-1)
                break;
            System.out.print(result + " ");
        }

        System.out.println("\n当前队列："+aq.getCount());
        System.out.println("入队："+aq.enqueue(2));
    }
}
