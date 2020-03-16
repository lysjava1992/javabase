package com.handbook.java.designmode.prototype;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *   浅克隆
 * @ClassName ShallowClone
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 21:08
 * @Version 1.0
 **/
public class ShallowClone implements Cloneable{
     Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


}
