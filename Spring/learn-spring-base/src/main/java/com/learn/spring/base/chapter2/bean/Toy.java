package com.learn.spring.base.chapter2.bean;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Toy
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 19:56
 * @Version 1.0
 **/
public class Toy {
    private String type;
    private String name;
    private String factory;
    private long serial;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", factory='" + factory + '\'' +
                ", serial=" + serial +
                '}';
    }
}
