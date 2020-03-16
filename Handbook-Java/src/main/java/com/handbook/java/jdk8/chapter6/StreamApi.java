package com.handbook.java.jdk8.chapter6;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName StreamApi
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 11:07
 * @Version 1.0
 **/
public class StreamApi {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl","jkl");
        List<Integer> numbers=Arrays.asList(3,4,5,1,2,6,9);
        //生成流 集合有两个方法生成流
            // stream()        − 为集合创建串行流。
            //parallelStream() − 为集合创建并行流。

       //forEach 迭代
         list.stream().forEach(System.out::println);
         System.out.println("==============");



        //map 用于映射每个元素到对应的结果
        List<Integer> results=numbers.stream().map(i->i*i).collect(Collectors.toList());
        results.stream().forEach(System.out::println);
        System.out.println("==============");


        //filter 条件过滤
        long count=list.stream().filter(str->str.isEmpty()).count();
        System.out.println("空字符串个数："+count);
        System.out.println("==============");
        //.distinct()去重
        List<String> list1=list.stream().filter(str->!str.isEmpty()).distinct().collect(Collectors.toList());
        list1.stream().forEach(System.out::println);
        System.out.println("==============");

        //limit 获取指定数量
        Random random=new Random();
        random.ints().limit(10).forEach(System.out::println);
        System.out.println("==============");
        //sorted 排序
        random.ints(1,100).limit(10).sorted().forEach(System.out::println);

        //Collectors.toList() 流->集合 Collectors.joining("_")流->字符串
        String result=list.stream().filter(str->!str.isEmpty()).collect(Collectors.joining("_"));
        System.out.println(result);

        // 统计 int double long 等基本类型
        IntSummaryStatistics stats=numbers.stream().mapToInt((x)->x).summaryStatistics();
        System.out.println("最大数："+stats.getMax());
        System.out.println("最小数："+stats.getMin());
        System.out.println("和："+stats.getSum());
        System.out.println("计数："+stats.getCount());
        System.out.println("平均数："+stats.getAverage());
    }
}
