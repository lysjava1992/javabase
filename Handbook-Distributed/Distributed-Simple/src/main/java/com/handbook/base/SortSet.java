package com.handbook.base;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SortSet
 *   sortedSet.first() 第一个元素 最小的元素
 *    SortedSet<String> lessThenMe=sortedSet.headSet("000006");
 *    lessThenMe 比"000006"小的元素的有序集合
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 14:13
 * @Version 1.0
 **/
public class SortSet {
    public static void main(String[] args) {
        SortedSet<String> sortedSet=new TreeSet<>();
        sortedSet.add("000015");
        sortedSet.add("000055");
        sortedSet.add("000005");
        sortedSet.add("000006");
        sortedSet.add("000001");
        sortedSet.add("000011");
        System.out.println(sortedSet.first());
        //结果000001
       SortedSet<String> lessThenMe=sortedSet.headSet("000006");
      System.out.println(lessThenMe.size());
    }
}
