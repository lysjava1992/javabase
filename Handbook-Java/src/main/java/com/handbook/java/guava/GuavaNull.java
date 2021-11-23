package com.handbook.java.guava;


import com.google.common.base.Optional;

import static com.google.common.base.Strings.*;

/**
 * 字符串判断
 */
public class GuavaNull {
    public static void main(String[] args) {
          // 判断字符串是否为null或者为""
          System.out.println(isNullOrEmpty(null));

          // 若字符串不为空或者"  "返回原值，
          // 若字符串为""则返回null
          System.out.println(emptyToNull(""));

           //只有字符串为null时
           //才会将目标字符串转为""
          System.out.println(nullToEmpty("  ").equals(""));
    }


    public static void options(){
        //Optional.of(null);会直接抛异常
        Optional<Integer> integer= Optional.of(6);

        //如果包含非null的引用则返回true 一直返回true
        System.out.println(integer.isPresent());
        //获取值
        System.out.println(integer.get());
        // 默认
        System.out.println(integer.or(12));
        System.out.println(integer.orNull());
    }
}
