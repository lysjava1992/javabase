package com.learn.spring.base.chapter6;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 *  环绕通知
 *
 * @ClassName CustomAroundAdvice
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 14:57
 * @Version 1.0
 **/
public class CustomAroundAdvice  implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("事物方法开始----");
        Object result=methodInvocation.proceed();
        System.out.println("事物方法结束----");
        return result;
    }
}
