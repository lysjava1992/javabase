package com.handbook.java.designmode.observer;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Mouse
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/26 11:37
 * @Version 1.0
 **/
public class Mouse extends EventLisenter{
    public void click(){
        System.out.println("鼠标操作：【单击】");
        this.trigger(MouseEventType.ON_CLICK);
    }
    public void doubleClick(){
        System.out.println("鼠标操作：【双击】");
        this.trigger(MouseEventType.ON_DOUBLE_CLICK);
    }
    public void up(){
        System.out.println("鼠标操作：【弹起】");
        this.trigger(MouseEventType.ON_UP);
    }
    public void down(){
        System.out.println("鼠标操作：【按下】");
        this.trigger(MouseEventType.ON_DOWN);
    }
    public void wheel(){
        System.out.println("鼠标操作：【滚动】");
        this.trigger(MouseEventType.ON_WHEEL);
    }
    public void move(){
        System.out.println("鼠标操作：【移动】");
        this.trigger(MouseEventType.ON_MOVE);
    }
    public void over(){
        System.out.println("鼠标操作：【悬停 】");
        this.trigger(MouseEventType.ON_OVER);
    }
}
