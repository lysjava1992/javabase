package com.me.leetcode;

/**
 *  判断是否是回文数
 *   从左到右 从右到左 一样的整数
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-09 08:41
 **/
public class Practice9 {
    public static void main(String[] args) {
         System.out.println(isPalindrome(2134567890));
    }
    public static boolean isPalindrome(int x) {
        if(x<0){return false;}
        if(x<10){return true;}
        if(x%10==0){return false;}
        int temp=x;
        long result=0;
        while (temp>0){
            result=result*10+(temp%10);
            temp/=10;
        }
            return x==result;
    }
}
