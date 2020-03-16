package com.handbook.java.datastructure.linkedlist;

public class DoublePointLinkedList {
    public static void main(String[] args) {
        Node node=new Node(1);
        for(int i=2;i<=15;i++){
            Node temp=new Node(i);
            node.pre=temp;
            temp.next=node;
            node=temp;
            node.pre=null;
        }
        Node  fastPointer=node;
        Node  slowPointer=node;
        while (fastPointer.next!=null){
            fastPointer=fastPointer.next;
            slowPointer=slowPointer.next;
            if(fastPointer.next!=null){
                fastPointer=fastPointer.next;
            }else {
                slowPointer=slowPointer.next;
            }
        }
        System.out.println(slowPointer.data);
//        while (node.next !=null){
//            System.out.print(node.next.pre.data+" ");
//            node=node.next;
//        }
    }
    private static class Node{
        private Integer data ;
        private Node pre;
        private Node next;

        public Node() {
        }

        public Node(Integer data) {
            this.data = data;
        }
    }
}
