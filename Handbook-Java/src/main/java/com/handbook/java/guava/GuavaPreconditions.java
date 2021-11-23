package com.handbook.java.guava;


import static com.google.common.base.Preconditions.*;

/**
 * 判断工具
 */
public class GuavaPreconditions {
    public static void main(String[] args) {
        String str=null;
        // checkArgument(表达式语句,"异常提示")
        // 当表达式语句为false抛出IllegalArgumentException
        checkArgument(str==null,"条件不符");

        // 非null判断，若字符串或对象为null则抛出NullPointerException异常
         Object o=1;
        checkNotNull(o,"对象为null");

        //对象状态检查 IllegalStateException
         checkState(1<5,"异常提示");

         //检查数组|集合是否有指定的下标IndexOutOfBoundsException
        // 若存在返回index,不存在抛异常
         String[] strs={"a","b"};
         // 如上strs长度为2
         // checkElementIndex会抛异常
         // checkPositionIndex不会报异常
        System.out.println(checkElementIndex(2,strs.length));
        System.out.println(checkPositionIndex(2,strs.length));

        // 判断以0开始2结束的下标位置在strs内
         checkPositionIndexes(0,2,strs.length);

    }
}
