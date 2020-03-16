package com.handbook.java.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @description:
 *   Stream 是 Java8中处理集合的关键抽象概念
 *             使用一种类似用SQL语句从数据库查询数据的直观方式来提供一种对java集合运算和表达的高阶抽象
 *             将要处理的元素集合看作一种流，流在管道中传输，并且可以在管道的节点上进行处理（筛选 排序 聚合等）
 *
 *  stream()串行流
 * @author: Mr.Luan
 * @create: 2019-12-10 14:04
 **/
public class Chapter5 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        /**
         *   Collection.stream() 获取串行流
         *    stream.filter(Predicate<? super T> predicate)  过滤  Predicate（返回bllolen）   过滤条件
         *    stream.collect(Collectors.toList())      收集进新list
         */
        List<String> filters=strings.stream().filter(s->!s.isEmpty()).collect(Collectors.toList());
        filters.forEach(System.out::println);

        System.out.println("=====================");
        /**
         * limit(5) 限制5个
         * sorted() 排序
         */
        Random random=new Random();
        random.ints(0,100).limit(5).sorted().forEach(System.out::println);
        System.out.println("=====================");
        /**
         * map(Function<? super T, ? extends R> mapper) 映射
         * distinct() 去重
         */
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> result=numbers.stream().map(n -> n*n).distinct().collect(Collectors.toList());
        result.forEach(System.out::println);


    }
}
