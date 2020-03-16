package com.handbook.java.lambda;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Optional;

/**
 * @description:
 *  Optional 类是一个可以为null的容器对象
 * @author: Mr.Luan
 * @create: 2019-12-10 16:33
 **/
public class Chapter8 {
    /**
     * 空值判断 不判断会出现异常
     * @param a
     * @param b
     * @return
     */
    public static int sum(Integer a,Integer b){
        if(a==null&&b==null){
            return 0;
        }else if(a==null||b==null){
            return a==null?b:a;
        }else {
            return a+b;
        }
    }

    public static int sum(Optional<Integer> a, Optional<Integer> b) {
         //10 为null时的默认值
        Integer value1=a.orElse(10);
        Integer value2=b.orElse(10);
        return value1+value2;
    }
    public static void main(String[] args) {
           Integer a=null;
           Integer b=2;
          System.out.println(sum(a,b));
          Optional<Integer> aa= Optional.ofNullable(a);
          Optional<Integer> bb=Optional.ofNullable(b);
        System.out.println(sum(aa,bb));
    }
}
