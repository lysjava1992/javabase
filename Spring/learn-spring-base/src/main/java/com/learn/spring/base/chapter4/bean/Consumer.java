package com.learn.spring.base.chapter4.bean;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Consumer
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 11:09
 * @Version 1.0
 **/
public class Consumer {
   private String name;
   private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
