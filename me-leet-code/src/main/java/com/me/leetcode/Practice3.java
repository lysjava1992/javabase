package com.me.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Solution3
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 8:01
 * @Version 1.0
 **/
public class Practice3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set=new HashSet<Character>();
            int left=0;
            int right=0;
            int count=0;
            char[] arr=s.toCharArray();
            for (int i=0;i<arr.length;i++){
                if(!set.contains(arr[i])){
                    count++;
                    right++;
                    set.add(arr[i]);
                }else {
                    set.remove(arr[left++]);
                }
            }
         return 0;
    }
}
