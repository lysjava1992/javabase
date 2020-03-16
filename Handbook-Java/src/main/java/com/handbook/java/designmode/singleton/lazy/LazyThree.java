package com.handbook.java.designmode.singleton.lazy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *        默认使用LazyThree时会先初始化内部类
 *        但不使用，内部类不会加载
 * @ClassName Lazy
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 16:43
 * @Version 1.0
 **/
public class LazyThree {

    private LazyThree() {

    }

    public static  LazyThree getInstance(){

        return LazyHoder.LAZY_THREE;
    }
    private static class LazyHoder{
        private static final LazyThree LAZY_THREE=new LazyThree();
    }


}
