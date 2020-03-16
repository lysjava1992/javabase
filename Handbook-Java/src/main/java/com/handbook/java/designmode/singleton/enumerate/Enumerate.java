package com.handbook.java.designmode.singleton.enumerate;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName enumerate
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 20:06
 * @Version 1.0
 **/
public enum Enumerate {
    /**
     *
     */
    INSTANCE;
    private Object object;

    Enumerate() {
        object=new Object();
    }

    public Object getInstance() {
        return object;
    }
}
