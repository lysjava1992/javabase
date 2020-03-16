package com.handbook.java.datastructure.queue;

public class LinkListQueue {
    private Node tail;
    private Node head;

    //入队
    public void enqueue(Integer i){
        if (tail == null) {
            Node newNode = new Node(i, null);
            head = newNode;
            tail = newNode;
        } else {
            tail.next = new Node(i, null);
            tail = tail.next;
        }
    }
    // 出队
    public int dequeue() {
        if (head == null) return -1;
        int value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }
    private static class Node{
        private Integer data;
        private Node next;
        public Node() {
        }
        public Node(Integer data) {
            this.data = data;
        }

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Integer getData() {
            return data;
        }
    }
}
