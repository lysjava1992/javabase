package com.handbook.java.thread;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Calculator
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/20 17:10
 * @Version 1.0
 **/
public class Calculator {
    private ImmutableValue currentValue=null;


    public   ImmutableValue getValue(){
        return currentValue;
    }

    public  void setValue(ImmutableValue newValue){
        this.currentValue = newValue;
    }

    public void add(int newValue){
        this.currentValue = this.currentValue.add(newValue);
    }
}
