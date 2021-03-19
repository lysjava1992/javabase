package com.handbook.java.reflection;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkAgent implements InvocationHandler{
    private Object target;

    public Object getInstance(Object target){
        System.out.println();
        this.target=target;
        Class<?>clazz=target.getClass();

        return  Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target,args);
    }
}
