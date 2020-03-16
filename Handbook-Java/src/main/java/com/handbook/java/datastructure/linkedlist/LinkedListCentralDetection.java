package com.handbook.java.datastructure.linkedlist;

/**
 * 链表中环检测
 */
public class LinkedListCentralDetection {
    public static void main(String[] args) {
      Node head=initNode(5);
      display(head);
      if(head==null){
          System.out.println("链表为空");
          return;
      }else if(head.next==null){
          System.out.println("链表只有一个值: "+head.data);
          return;
      }else if(head.next.next==null){
          System.out.println("链表只有两个值: "+head.data);
          return;
      }else {
          Node  fastPointer=head;
          Node  slowPointer=head;
          while (fastPointer.next!=null){
                 fastPointer=fastPointer.next;
                 slowPointer=slowPointer.next;
                 if(fastPointer.next!=null){
                     fastPointer=fastPointer.next;
                 }
          }
          System.out.println("中环值为:  "+slowPointer.data);
      }
    }

    private static Node initNode(int count) {
        Node head=new Node(1);
        for (int i=2;i<=count;i++){
            Node temp=new Node(i);
            temp.next=head;
            head=temp;
        }
        return head;
    }
   private static void  display(Node head){
        System.out.println("当前链表结构");
        while (head !=null){
            System.out.print(head.data+" ");
            head=head.next;
        }
        System.out.println();
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
