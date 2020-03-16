package com.handbook.java.designmode.singleton.lazy;

import java.io.Serializable;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *        延迟加载 用的时候new
 * @ClassName Lazy
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 16:43
 * @Version 1.0
 **/
public class LazyOne  implements Serializable{
    private LazyOne() {
    }
    private static LazyOne lazy;
    public static LazyOne getInstance(){
        if(lazy==null){
            lazy=new LazyOne();
        }
        return lazy;
    }
    private Object readResolve(){
        return lazy;
    }
}
