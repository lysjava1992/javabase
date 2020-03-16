package com.me.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 *   返回第一个不重复的字母
 * @author: Mr.Luan
 * @create: 2020-03-13 14:23
 **/
public class Practice387 {
    public static void main(String[] args) {
        String s="loveleetcode";
       System.out.println(firstUniqChar(s));
    }

    /**
     *   用HashMap统计 次数
     *   遍历找第一个
     * @param s
     * @return
     */
    private static int firstUniqChar(String s) {
        int index=-1;
        char[] arr=s.toCharArray();
        Map<Character,Integer> map= new HashMap<>();
        for (char c:arr){ map.merge(c,1,Integer::sum); }
        for (int i=0;i<arr.length;i++){
            if(map.get(arr[i])==1){
                return i;
            }
        }
        return index;
    }
}
