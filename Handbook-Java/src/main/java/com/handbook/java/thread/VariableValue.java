package com.handbook.java.thread;

import java.util.Random;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName VariableValue
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/21 14:58
 * @Version 1.0
 **/
public class VariableValue {
    private int value=0;
    public  int getValue() {
        return value;
    }
    public  void  setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        VariableValue variableValue=new VariableValue();
        Random random=new Random();
        for (int i = 0; i <2 ; i++) {
            Thread thread=new Thread(() -> {
              int value=random.nextInt();
                variableValue.setValue(value);
               System.out.println(value+"  -  "+variableValue.getValue());
            });
            thread.start();
        }
    }
}
