package com.learn.spring.base.chapter5;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;


/**
 * AOP 通知类型
 *         MethodBeforeAdvice       前置通知  方法前
 *         AfterReturningAdvice     后置通知  方法后
 *         ThrowsAdvice             异常通知  异常出现
 *         MethodInterceptor       环绕通知  前+后
 * @ClassName Custom
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 14:11
 * @Version 1.0
 **/

public class CustomBeforeAdvice  implements MethodBeforeAdvice {
    public void before(Method method, Object[] objects, Object o) throws Throwable {
           System.out.println("前置通知");
    }
}
