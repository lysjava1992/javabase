package com.learn.spring.base.chapter1.service;

import com.learn.spring.base.chapter1.bean.Person;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName DoSomethingImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 19:28
 * @Version 1.0
 **/
public class DoSomethingImpl implements DemoService {
    private Person person;
    public DoSomethingImpl() {

    }
    public DoSomethingImpl(Person person) {
        this.person = person;
    }

    public void doSomething() {
        System.out.println(person.getName()+":I'm Working!!!");
    }
}
