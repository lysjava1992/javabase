package com.handbook.java.datastructure.recurs;

public class RecursLinkedList {
    public static void main(String[] args) {
        Node head = new Node(10);
        for (int i = 9; i > 0; i--) {
            Node now = new Node(i);
            now.next = head;
            head = now;
        }
     Node now=recurs1(head);
        while (now!=null){
            System.out.print(now.data+" ");
            now=now.next;
        }
    }
    private static Node recurs(Node node) {
        if (node.next == null) {
            return node;
        }
        Node now = recurs(node.next);
        node.next.next = node;
        node.next=null;
        return now;
    }
    private static Node recurs1(Node node) {
        if (node.next == null) {
            return node;
        }
        Node next = node.next;
        node.next = null;
        Node now = recurs(next);
        next.next= node;
        return now;
    }
    private static class Node {
        private Integer data;
        private Node next;
        public Node() {
        }
        public Node(Integer data) {
            this.data = data;
        }
    }
}
