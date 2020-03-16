package com.handbook.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *     常量池
 *     配置： -XX:PermSize=10M -XX:MaxPermSize=10M
 *            方法区大小
 *    str.intern()
 *        判断这个常量是否存在于常量池，
 *    如果存在，
 *     判断这个常量是存在的引用还是常量，
 *      如果是引用，返回引用地址指向的堆空间对象，
 *      如果是常量，则直接返回常量池常量，
 *    如果不存在，
 *      在常量池中创建该常量，并返回此常量
 *
 * @ClassName RuntimeConstantPoolOOM
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 19:00
 * @Version 1.0
 **/
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        /**
         *   List<String> list=new ArrayList<>();
         *  int i=0;
         *   while (true){
         *      list.add(String.valueOf(i++).intern());
         *   }
         *
         *
         */

        String str1=new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);
        //true


        String str2=new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
        //false
    }
}
