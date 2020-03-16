package com.me.leetcode;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-13 15:50
 **/
public class Practice28 {
    public static void main(String[] args) {
        String haystack = "1";
        String needle = "1";
        System.out.println(strStr1(haystack, needle));
        System.out.println(strStr2(haystack, needle));
        System.out.println(strStr3(haystack, needle));
        System.out.println(strStr4(haystack, needle));
    }

    private static int strStr4(String haystack, String needle) {
        if (needle.length() == 0) { return 0; }
        if (needle.length() > haystack.length()) { return -1; }
        int haystack_l=haystack.length();
        int needle_l=needle.length();
        for (int i=0;i<=haystack_l-needle_l;i++){
            String str=haystack.substring(i,i+needle_l);
            if(str.equals(needle)){return i;}
        }
        return -1;
    }

    private static int strStr3(String haystack, String needle) {
        if (needle.length() == 0) { return 0; }
        if (needle.length() > haystack.length()) { return -1; }
        for (int i=0;i<=haystack.length()-needle.length();i++){
             int temp_i=i;
           for (int j=0;j<needle.length();j++,temp_i++){

               if(haystack.charAt(temp_i)!=needle.charAt(j)){break;}
               if(j==needle.length()-1){return i;}
           }
        }
        return -1;
    }

    private static int strStr2(String haystack, String needle) {
        if (needle.length() == 0) { return 0; }
        if (needle.length() > haystack.length()) { return -1; }
        char[] chars_haystack = haystack.toCharArray();
        char[] chars_needle = needle.toCharArray();
        int index = -1;
        boolean status = true;
        for (int i = 0; i < chars_haystack.length; i++) {
            int temp_i = i;
            for (int j = 0; j < chars_needle.length && temp_i < chars_haystack.length; j++, temp_i++) {
                if (chars_haystack[temp_i] != chars_needle[j]) {
                    status = false;
                    break;
                }
                if (j == chars_needle.length - 1) {
                    status = true;
                }
            }
            if (status) {
                return i;
            }
        }
        return index;
    }

    private static int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
