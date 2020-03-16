package com.handbook.java.designmode.observer;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MouseEventCallbak
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/26 12:37
 * @Version 1.0
 **/
public class MouseEventCallbak {
    public void onClick(Event e){
       System.out.println("====鼠标单击事件=====\n"+e);
    }
    public void onDoubleClick(Event e){
        System.out.println("====鼠标双击事件=====\n"+e);
    }
    public void onUp(Event e){
        System.out.println("====鼠标弹起事件=====\n"+e);
    }
    public void onDown(Event e){
        System.out.println("====鼠标按下事件=====\n"+e);
    }
    public void onMove(Event e){
        System.out.println("====鼠标移动事件=====\n"+e);
    }
    public void onWeel(Event e){
        System.out.println("====鼠标滚动事件=====\n"+e);
    }
    public void onOver(Event e){
        System.out.println("====鼠标悬浮事件=====\n"+e);
    }

}







