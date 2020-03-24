package com.me.leetcode;

/**
 * @description:
 * 链表中环检测
 * @author: Mr.Luan
 * @create: 2020-03-23 09:05
 **/
public class Practice141 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node2;
        node3.next = node4;
        node4.next = node5;
        System.out.println(hasCycle(node1));

    }
    public static boolean hasCycle(ListNode head) {
        if(head==null||head.next==null){return false;}
        ListNode fast=head.next;
        ListNode slow=head;
        while (fast!=null){
            if(fast==slow){
                return true; }
            slow=slow.next;
            fast=fast.next.next;
        }
        return false;
    }
}
