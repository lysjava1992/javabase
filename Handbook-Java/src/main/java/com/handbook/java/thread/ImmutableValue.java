package com.handbook.java.thread;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ImmutableValue
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/20 17:00
 * @Version 1.0
 **/
public class ImmutableValue {
    /**
     * private 修饰
     * 只有get方法访问
     * 不变性
     */
    private int value;

    public ImmutableValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public ImmutableValue add(int valueToAdd){
        return new ImmutableValue(value+valueToAdd);
    }
}
