package com.me.leetcode;

import java.util.*;

/**
 *  异位字母分组
 *  输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 *  输出:
 *  [
 *  ["ate","eat","tea"],
 *  ["nat","tan"],
 *  ["bat"]
 *  ]
 *
 * @ClassName Pratice499
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/29 11:25
 * @Version 1.0
 **/
public class Practice49 {
    public static void main(String[] args) {
        String[] stars={"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> list=  groupAnagrams(stars);
        for (List<String> li:list) {
            for (String str:li) {
                System.out.print(str+"    ");
            }
            System.out.println();
        }
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String ,List<String>>map=new HashMap<>();
        for (String str:strs) {
            char[] chrs=str.toCharArray();
            Arrays.sort(chrs);
            String temp=String.valueOf(chrs);
            if(map.containsKey(temp)){
                map.get(temp).add(str);
            }else {
                List<String> list=new ArrayList<>();
                list.add(str);
                map.put(temp,list);
            }
        }
        List<List<String>> result=new ArrayList<>();
        for (Map.Entry<String, List<String>> m:map.entrySet()){
           result.add(m.getValue());
        }
     return new ArrayList<>(map.values());
    }

}
