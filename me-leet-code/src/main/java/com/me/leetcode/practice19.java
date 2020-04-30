package com.me.leetcode;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Practice
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/18 19:25
 * @Version 1.0
 **/
public class Practice19 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node1 = removeNthFromEnd2(node1, 5);
        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.next;
        }
    }

    private static ListNode removeNthFromEnd2(ListNode node1, int i) {
         ListNode step=new ListNode(0);
         step.next=node1;
         ListNode step1=step;
         ListNode step2=step;
         int index=0;
         while (index<i){
             step2=step2.next;
             index++;
         }
         while (step2.next!=null){
             step1=step1.next;
             step2=step2.next;
         }
        step1.next=step1.next.next;
         return step.next;
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        if (length == n) {

            return head.next;
        }
        n = length - n;
        int index = 1;
        temp = head;
        while (index < n) {
            temp = temp.next;
            index++;
        }
        temp.next = temp.next.next;
        return head;
    }
}
