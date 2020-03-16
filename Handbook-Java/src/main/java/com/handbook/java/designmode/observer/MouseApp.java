package com.handbook.java.designmode.observer;

import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MouseApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/26 12:44
 * @Version 1.0
 **/
public class MouseApp {
    public static void main(String[] args) {


        try {
            MouseEventCallbak callbak=new MouseEventCallbak();
            Method onClick=MouseEventCallbak.class.getMethod("onClick", Event.class);
            Mouse mouse=new Mouse();
            mouse.addLisenter(MouseEventType.ON_CLICK,callbak,onClick);
            mouse.click();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}








