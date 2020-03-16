package com.handbook.java.jdk8.chapter7;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName App
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 11:46
 * @Version 1.0
 **/
public class App {
    public static void main(String[] args) throws IOException {
         Integer a=null;
         Integer b=12;
         //允许传入null
        Optional<Integer> oa=Optional.ofNullable(a);
        //传入null会报空指针异常Optional.of(b)  .get()获取值
        Optional<Integer> ob=Optional.ofNullable(b);;

         System.out.println(oldAdd(a,b));
         System.out.println(newAdd(oa,ob));
         Scanner scanner=new Scanner(System.in);
         String str= scanner.nextLine();
         System.out.println(str);
    }

    private static Integer newAdd(Optional<Integer> oa, Optional<Integer> ob) {
        Integer a=oa.orElse(new Integer(0));
        Integer b=ob.orElse(new Integer(0));
        return a+b;
    }

    private static Integer oldAdd(Integer a, Integer b) {
        if(a==null&&b==null){
            return 0;
        }else if (a==null||b==null){
            return a==null?b:a;
        }else {
            return a+b;
        }
    }

}
