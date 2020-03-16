package com.handbook.java.designmode.proxy.dynamic.jdk;

import com.handbook.java.designmode.proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**

 *
 * @ClassName JdkAgent
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 15:15
 * @Version 1.0
 *
 *  JDK动态代理 implements InvocationHandler
 *
 **/
public class JdkAgent implements InvocationHandler {
    private Person person;

    /**
     * 返回一个代理类
     * @param person
     * @return
     */
    public Object getInstance(Person person){
         this.person=person;
         Class<?>clazz=person.getClass();
          return  Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK 代理类：我是代理");
        method.invoke(this.person,args);
        return null;
    }
}
