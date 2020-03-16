package com.me.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 外观序列
 *   1.     1
 *   2.     11
 *   3.     21
 *   4.     1211
 *   5.     111221
 *   1 ≤ n ≤ 30
 * @ClassName Practice38
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 14:33
 * @Version 1.0
 **/
public class Practice38 {
    public static void main(String[] args) {

        System.out.println("12333333333".charAt(1));
      String str=countAndSay1(5);
      String str2=countAndSay2(5);
       System.out.println(str);
       System.out.println(str2);
    }

    private static String countAndSay2(int n) {
        String result="1";
        for (int i=1;i<n;i++){
            StringBuilder sb=new StringBuilder();
            char value=result.charAt(0);
            int count=1;
            for (int j=1;j<result.length();j++){
                if(value==result.charAt(j)){
                    count++;
                    continue;
                }
                sb.append(count);
                sb.append(value);
                value=result.charAt(j);
                count=1;
            }
            sb.append(count);
            sb.append(value);
            result=sb.toString();
        }
        return result;

    }

    private static String countAndSay1(int i) {
        int index=1;
        List<Integer>list=new ArrayList<>();
        list.add(1);
        list=iteration(list,index,i);
        String str="";
        for (int value:list){
            str+=value;
        }
        return str;
    }

    private static List<Integer> iteration(List<Integer> list, int index,int  item) {
        if(index==item){return list;}
        List<Integer> results=new ArrayList<>();
        int value=list.get(0);
        int count=1;
        for (int i=1;i<list.size();i++){
            if(list.get(i)==value){
                count++;
            }else {
                results.add(count);
                results.add(value);
                value=list.get(i);
                count=1;
            }
        }
        results.add(count);
        results.add(value);
        index+=1;
        return iteration(results,index, item);
    }
}
