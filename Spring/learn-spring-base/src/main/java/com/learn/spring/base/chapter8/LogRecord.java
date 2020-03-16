package com.learn.spring.base.chapter8;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName LogRecord
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 16:07
 * @Version 1.0
 **/
@Aspect
@Component
public class LogRecord {
    /**
     * 定义一个方法，用于声明切入点的表达式，
     * 使用@Pointcut来声明切入点表达式
     * 后面的其他通知直接使用方法名来引用切入点表达式
     *     包下所有类方法 不包含子包 但不能包含接口
     * execution(* com.learn.spring.base.chapter8.service.*())
     */
    @Pointcut("execution(* com.learn.spring.base.chapter8.service.*Impl.*())")
    public void  anyAll(){}
    @Pointcut("execution(* com.learn.spring.base.chapter8.service.*Impl.select())")
    public void  select(){}
    @Pointcut("execution(* com.learn.spring.base.chapter8.service.*Impl.remove())")
    public void  remove(){}

    @Before("anyAll()")
    public void before(){
        System.out.println("处理请求");
    }

    @AfterReturning("select()")
    public void after(){
        System.out.println("事物结束");
    }

    @Around("remove()")
     public void around(ProceedingJoinPoint point) throws Throwable {
         System.out.println("删除操作开始");
         point.proceed();
         System.out.println("删除操作结束");
     }
}
