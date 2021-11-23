package com.handbook.java.guava;

import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 排序器
 */
public class GuavaOrder {
    public static void main(String[] args) {
        orderingNull();

    }



    /**
     * 当待排序集合中含有null时
     * 使用原生排序会报错
     * guava提供了方法 允许null值参加排序，
     * 并将null放在队首nullsFirst 或 队尾nullsLast
     *
     * isOrdered 判断是否是有序的
     */
    public static void orderingNull(){
        List<Integer> list=Arrays.asList(1,5,7,100,98,3,2,1,null);
        Collections.sort(list,Ordering.natural().nullsFirst());
        list.forEach(System.out::print);
        System.out.println(Ordering.natural().nullsFirst().isOrdered(list));
    }

    /**
     * 内置和自定义排序器
     */
    public static void  ordering(){
        // 定义排序器
        com.google.common.collect.Ordering<String> byLengthOrdering=new Ordering<String>() {
            @Override
            public int compare(String s, String t1) {
                return com.google.common.primitives.Ints.compare(s.length(),t1.length());
            }
        };

        List<String> list= Arrays.asList("a","abd","a","ac","acvb");
        // java 排序
        //Collections.sort(list);

        //自然配置
        // Collections.sort(list,Ordering.natural());
        //以字符串排序
        // Collections.sort(list,Ordering.usingToString());
        // 以自定义排序
        // Collections.sort(list,byLengthOrdering);
        list.forEach(x->System.out.print(x+"  "));


        List<Integer> list2=Arrays.asList(1,5,7,100,98,3,2,1,null);
        // 默认从小到大
        Collections.sort(list2,Ordering.natural().nullsFirst());
        Collections.sort(list2);
        Collections.sort(list2,Ordering.natural());
        // 反转从大到小
        Collections.sort(list2,Ordering.natural().reverse());
        // 获取最大值
        System.out.println(Ordering.natural().max(list2));
        list2.forEach(i->{System.out.print(i+"  ");});
    }
}
