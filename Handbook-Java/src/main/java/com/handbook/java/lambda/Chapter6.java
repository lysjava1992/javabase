package com.handbook.java.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-12-10 15:49
 **/
public class Chapter6 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        System.out.println("空字符串数量: "+strings.stream().filter(s->s.isEmpty()).count());
        //过滤掉空字符串
        List<String> list=strings.stream().filter(s->!s.isEmpty()).collect(Collectors.toList());
        list.forEach(System.out::println);
        //过滤空字符串并合并
        String str=strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining("-"));
        System.out.println(str);


        List<Integer> numbers = Arrays.asList(3, 2, 1, 5, 6, 0, 7);
        IntSummaryStatistics statistics=numbers.stream().mapToInt(n->n).summaryStatistics();
        System.out.println("最大数 :"+statistics.getMax());
        System.out.println("最小数 :"+statistics.getMin());
        System.out.println("和 :"+statistics.getSum());
        System.out.println("平均数 :"+statistics.getAverage());
    }
}
