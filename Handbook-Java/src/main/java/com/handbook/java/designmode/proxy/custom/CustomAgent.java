package com.handbook.java.designmode.proxy.custom;

import com.handbook.java.designmode.proxy.Person;

import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CustomAgent
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 16:40
 * @Version 1.0
 **/
public class CustomAgent implements CustomInvocationHandler {
    private Person target;

    public Object getInstance(Person person){
        this.target=person;
        Class<?>clazz=target.getClass();
        return CustomProxy.newProxyInstance(new CustomClassLoader(),clazz.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Custom 代理类：代理");
        method.invoke(this.target,args);
        return null;
    }
}
