package com.handbook.java.designmode.proxy.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *  需要 Cglib 依赖包
 * @ClassName CglibAgent
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 15:32
 * @Version 1.0
 **/
public class CglibAgent implements MethodInterceptor {

    public Object getInstance(Object object){
           Class clazz=object.getClass();
           Enhancer enhancer=new Enhancer();
           //将这个类设置为父类
           enhancer.setSuperclass(clazz);
           //调用者
           enhancer.setCallback(this);

           return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib 代理类：我是代理");
       methodProxy.invokeSuper(o,objects);

        return null;
    }
}
