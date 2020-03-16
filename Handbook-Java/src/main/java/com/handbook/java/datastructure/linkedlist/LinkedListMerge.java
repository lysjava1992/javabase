package com.handbook.java.datastructure.linkedlist;

/**
 *
 */
public class LinkedListMerge {
    public static void main(String[] args) {
     Node la=new Node(26);
     for(int i=5;i>2;i--){
         Node tmp=new Node(i*i);
         tmp.next=la;
         la=tmp;
     }
     Node latmp=la;
     System.out.println("la 链表数据：");
     while (latmp!=null){
         System.out.print(latmp.data+" ");
         latmp=latmp.next;
     }
     Node lb=new Node(15);
     for(int i=6;i>3;i--){
         Node tmp=new Node(i+i);
         tmp.next=lb;
         lb=tmp;
     }
        System.out.println("\nlb 链表数据：");
        Node lbtmp=lb;
        while (lbtmp!=null){
            System.out.print(lbtmp.data+" ");
            lbtmp=lbtmp.next;
        }

        Node head=mergeSortedLists(la,lb);
        System.out.println("\n 新数据：");
        while (head!=null){
            System.out.print(head.data+" ");
            head=head.next;
        }


    }
    // 有序链表合并
    public static Node mergeSortedLists(Node la, Node lb) {
        if (la == null) return lb;
        if (lb == null) return la;

        Node p = la;
        Node q = lb;
        Node head;
        if(p.data<q.data){
            head=p;
            p=p.next;
        }else {
            head=q;
            q=q.next;
        }
        Node temp=head;
       while (p !=null && q !=null){
            if(p.data<q.data){
                temp.next=p;
                p=p.next;
            }else {
                temp.next=q;
                q=q.next;
            }
           temp=temp.next;
       }
        if (p != null) {
            temp.next = p;
        } else {
            temp.next = q;
        }
        return head;
    }

    public static class  Node{
        private Integer data;
        private Node next;
        public Node() {
        }
        public Node(Integer data) {
            this.data = data;
        }
    }
}
