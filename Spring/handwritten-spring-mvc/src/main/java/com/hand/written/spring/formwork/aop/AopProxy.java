package com.hand.written.spring.formwork.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName AopProxy
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/10 12:00
 * @Version 1.0
 **/
public class AopProxy implements InvocationHandler{
    private AopConfig config;
    private Object target;
    public void setConfig(AopConfig config) {
        this.config = config;
    }

    /**
     *
     * @param instance  原生对象
     * @return  代理对象
     */
    public Object getProxy(Object instance) {
        this.target=instance;
        Class<?> clazz=instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m=this.target.getClass().getMethod(method.getName(),method.getParameterTypes());
        //原始调用前增强方法
        if(config.contains(m)){
            AopConfig.Aspect aspect=config.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect());
        }
        //原始方法调用
        Object object=method.invoke(this.target,args);

        //原始方法调用之后执行方法
        if(config.contains(m)){
            AopConfig.Aspect aspect=config.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect());
        }
        return object;
    }
}
