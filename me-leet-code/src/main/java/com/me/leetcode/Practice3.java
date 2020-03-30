package com.me.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * abcabcbb          abc
 * bbbbb             b
 * pwwkew            wke
 * @ClassName Solution3
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 8:01
 * @Version 1.0
 **/
public class Practice3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring1("dvdf"));
        System.out.println(lengthOfLongestSubstring2("dvdf"));
    }

    private static int lengthOfLongestSubstring2(String s) {
        HashMap<Character,Integer> map=new HashMap<>();
         int count=0;
         int start=0;
         int end=0;
        for (; end<s.length() ; end++) {
            if(map.containsKey(s.charAt(end))){
                start=Math.max(map.get(s.charAt(end)),start);

            }
            count=Math.max(count,(end-start+1));
            map.put(s.charAt(end),end+1);
        }
        return count;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        int count=0;
        for (int i = 0; i < s.length()-count; i++) {
            Set<Character> sets=new HashSet<>();
            sets.add(s.charAt(i));
            for (int j = i+1; j < s.length(); j++) {
                if(sets.contains(s.charAt(j))){
                    break;
                }
                sets.add(s.charAt(j));
            }
            count=sets.size()>count?sets.size():count;
        }
        return count;
    }
}
