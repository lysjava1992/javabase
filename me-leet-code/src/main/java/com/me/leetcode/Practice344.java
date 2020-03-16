package com.me.leetcode;

import java.util.Arrays;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-13 13:36
 **/
public class Practice344 {
    public static void main(String[] args) {
        char[] arr={'H','a','n','n','a','h'};
       // reverseString1(arr);
       // reverseString2(arr);
        reverseString3(arr);
    }



    /**
     * 需要额外的空间
     * @param s
     */
    private static void reverseString1(char[] s) {
        char[] arr=new char[s.length];
        int index=0;
        for (int i=s.length-1;i>=0;i--){
            arr[index++]=s[i];
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 反转 即中心两边互换位置
     * @param s
     */
    private static void reverseString2(char[] s) {
        if(s.length<=0){return;}
        int middle=s.length/2;

        for (int i=0;i<=middle;i++){
            char temp=s[i];
            s[i]=s[s.length-1-i];
            s[s.length-1-i]=temp;
        }
        System.out.println(Arrays.toString(s));
    }
    private static void reverseString3(char[] s) {
        int start=0;
        int end=s.length-1;
        while (start<=end){
            char temp=s[start];
            s[start++]=s[end];
            s[end--]=temp;
        }
        System.out.println(Arrays.toString(s));
    }

}
