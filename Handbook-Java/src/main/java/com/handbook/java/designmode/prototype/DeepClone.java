package com.handbook.java.designmode.prototype;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName DeepClone
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 21:09
 * @Version 1.0
 **/

public class DeepClone implements Cloneable{
    Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * 深度克隆 即重写clone方法 在其中将引用类型重新new
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object object=new Object();
        DeepClone clone= (DeepClone) super.clone();
        clone.setObject(object);
        return clone;

    }
}
