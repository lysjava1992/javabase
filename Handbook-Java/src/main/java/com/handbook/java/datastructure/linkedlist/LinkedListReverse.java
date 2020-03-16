package com.handbook.java.datastructure.linkedlist;


public class LinkedListReverse {
    public static void main(String[] args) {
    Node head=new Node(0);
    for(int i=1;i<20;i++){
        Node now=new Node(i);
        now.next=head;
        head=now;
     }
     Node temp=head;
    System.out.println("初始链表结构**********");
     while (temp!=null){
         System.out.print(temp.data+" ");
         temp=temp.next;
     }
     System.out.println("\n链表反转结构**********");
     Node node=Reverse3(head);
        while (node!=null){
            System.out.print(node.data+" ");
            node=node.next;
        }
    }

    /**
     * 迭代反转
     * @param node
     * @return
     */
    public static Node Reverse1(Node node){
        Node pre=null;
        while (node !=null){
             Node temp=node.next;
             node.next=pre;
             pre=node;
             node=temp;
        }
        return pre;
    }

    /**
     * 递归反转
     * @param node
     * @return
     */
    public static Node Reverse2(Node node){
        if(node ==null||node.next==null){
            return node;
        }
        Node now=Reverse2(node.next);
        node.next.next=node;
        node.next=null;
      return now;
    }
    /**
     * 递归反转
     * @param node
     * @return
     */
    public static Node Reverse3(Node node){
      if(node.next==null){
          return node;
      }
       Node next=node.next;
       node.next=null;
       Node now= Reverse3(next);
       next.next=node;
       return now;
    }
    public static class Node{
        private Integer data;
        private Node next;
        public Node() {

        }
        public Node(Integer data) {
            this.data = data;
        }
    }

}
