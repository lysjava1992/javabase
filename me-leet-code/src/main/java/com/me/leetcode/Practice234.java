package com.me.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回文链表检测
 *
 * @ClassName Paractice231
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/21 10:42
 * @Version 1.0
 **/
public class Practice234 {
    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
       ListNode node4=new ListNode(3);
        ListNode node5=new ListNode(2);
        ListNode node6=new ListNode(1);
        head.next=node2;
        node2.next=node3;
        node3.next=node4;
        //node3.next=node5;
        node4.next=node5;
        node5.next=node6;
        System.out.println(isPalindrome1(head));
        System.out.println(isPalindrome2(head));
    }

    /**
     *  快慢指针 将慢指针逆序
     * @param head
     * @return
     */
    private static boolean isPalindrome2(ListNode head) {
        if(head==null||head.next==null){return true;}
        ListNode fast=head,slow=head;
        ListNode newNode=head,pre=null;
        while (fast!=null&&fast.next!=null){
            newNode=slow;
            slow=slow.next;
            fast=fast.next.next;
            newNode.next=pre;
            pre=newNode;
        }
        if(fast!=null){
             slow=slow.next;
        }
        while (newNode!=null){
            if(newNode.val!=slow.val){
                return false;
            };
            newNode=newNode.next;
            slow=slow.next;
        }
        return true;
    }

    /**
     * 转数组
     * @param head
     * @return
     */
    public static boolean isPalindrome1(ListNode head) {
        List<Integer> list=new ArrayList<>();
        while (head!=null){
            list.add(head.val);
            head=head.next;
        }
        if(list.size()==1){return true;}
        for (int i = 0; i <list.size()/2 ; i++) {
            if(!list.get(i).equals(list.get(list.size() - 1 - i))){
                return false;
            }
        }
        return true;
    }
}
