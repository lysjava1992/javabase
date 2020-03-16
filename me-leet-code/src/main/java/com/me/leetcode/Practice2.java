package com.me.leetcode;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * 力扣2
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @ClassName Solution
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/22 20:11
 * @Version 1.0
 **/
public class Practice2 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(0));

        ListNode num1_1=new ListNode(1);
        ListNode num1_2=new ListNode(2);
        ListNode num1_3=new ListNode(3);
        //1->2->3     321
        num1_1.next=num1_2;
        num1_2.next=num1_3;
        ListNode num2_1=new ListNode(1);
        ListNode num2_2=new ListNode(2);
        ListNode num2_3=new ListNode(3);
        ListNode num2_4=new ListNode(3);
        //1->2->3->3  3321
        num2_1.next=num2_2;
        num2_2.next=num2_3;
        num2_3.next=num2_4;
        ListNode node=addTwoNumbers1(num1_1,num2_1);
        while (node!=null){
          System.out.println(node.val);
          node=node.next;
        }
    }
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(100);
        ListNode newList=head;
        int next=0;
        while (l1!=null&&l2!=null){
            int temp=(l1.val+l2.val+next);
            if(temp<10){
                next=0;
            }else {
                next=1;
                temp=temp-10;
            }
            ListNode node=new ListNode(temp);
            head.next=node;
            head=head.next;
            l1=l1.next;
            l2=l2.next;
        }
       while (l1!=null){
           int temp=l1.val+next;
           if(temp<10){
               next=0;
           }else {
               next=1;
               temp=temp-10;
           }
           ListNode node=new ListNode(temp);
           head.next=node;
           head=head.next;
           l1=l1.next;

       }
        while (l2!=null){
            int temp=l2.val+next;
            if(temp<10){
                next=0;
            }else {
                next=1;
                temp=temp-10;
            }
            ListNode node=new ListNode(temp);
            head.next=node;
            head=head.next;
            l2=l2.next;
        }

           if(next==0){
            return newList.next;
           }
        ListNode node=new ListNode(1);
        head.next=node;
        head=head.next;
        return newList.next;
    }
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(100);
        ListNode newList=head;
        int next=0;
        while (l1!=null||l2!=null){
              int n=(l1==null)?0:l1.val;
              int m=(l2==null)?0:l2.val;
              if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
              int value=m+n+next;
              if(value<10){
                  next=0;
              }else {
                  next=1;
                  value-=10;
              }
              ListNode node=new ListNode(value);
               head.next=node;
               head=head.next;
        }
        if(next==1){
            ListNode node=new ListNode(1);
            head.next=node;
            head=head.next;
        }
        return newList.next;
    }

    public static boolean isPalindrome(int x) {
        if(x<0||(x%10==0)){
            return false;
        }
        if(x==0){
            return true;
        }
        int reversal=0;
        while (reversal<x){
                reversal=reversal*10+(x%10);
                x=x/10;
        }

        return x==reversal||x==reversal/10;
    }
}
