package com.handbook.java.lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 *   Stream 是 Java8中处理集合的关键抽象概念
 *             使用一种类似用SQL语句从数据库查询数据的直观方式来提供一种对java集合运算和表达的高阶抽象
 *             将要处理的元素集合看作一种流，流在管道中传输，并且可以在管道的节点上进行处理（筛选 排序 聚合等）
 *
 *  parallelStream() 多线程操作
 * @author: Mr.Luan
 * @create: 2019-12-10 14:04
 **/
public class Chapter51 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        long countEmpty= strings.parallelStream().filter(str->str.isEmpty()).count();
        System.out.println(countEmpty);

        List<Integer> list=new ArrayList<>();
        for (int i=0;i<10000;i++){
            list.add(i);
        }
        long start=System.currentTimeMillis();
        list.forEach(System.out::println);
        long end=System.currentTimeMillis();
        System.out.println((end-start)+"=================");
        list.stream().forEach(System.out::println);
         end=System.currentTimeMillis();
        System.out.println((end-start)+"=================");
        // 输出无序
        list.parallelStream().forEach(System.out::println);
         end=System.currentTimeMillis();
        System.out.println((end-start)+"=================");
    }
}
