package com.me.datastructure.queue;

/**
 * 基于链表 的队列
 *
 * @ClassName NodeQueue
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/5/10 11:36
 * @Version 1.0
 **/
public class NodeQueue {
    private int capacity = 6;
    private int count;
    private ListNode head;
    private ListNode tail;

    public NodeQueue() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    public NodeQueue(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    public String dequeue() {
        String result = null;
        if (count > 0) {
            result = head.val;
            head = head.next;
            count--;
        }
        return result;
    }

    public boolean enqueue(String item) {
        if (count >= capacity) {
            return false;
        }
        ListNode node = new ListNode(item);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail=node;
        }
        count++;
        return true;
    }

    static class ListNode {
        String val;
        ListNode next;

        ListNode(String x) {
            val = x;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int capacity = 2;
        NodeQueue queue = new NodeQueue(capacity);
        for (int i = 0; i < capacity; i++) {
            queue.enqueue(i+" ");
        }
        while (true){
            System.out.println("出队: "+queue.dequeue());
            queue.enqueue(capacity+"");
            System.out.println("入队: "+capacity++);
            Thread.sleep(1000);
        }
    }
}
