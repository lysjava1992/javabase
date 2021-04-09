package com.learn.spring.base.chapter25;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ProfilingAspect
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/4/3 11:28
 * @Version 1.0
 **/
@Component
@Aspect
public class ProfilingAspect {

    @Pointcut("execution(*  com.learn.spring.base.chapter25.StubEntitlementCalculationService.*(..))")
    public void methodsToBeProfiled(){}

    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint pjp)throws Throwable{
        StopWatch sw=new StopWatch(getClass().getSimpleName());
        System.out.println("+++++++++++");
        try {
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        }finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }

    }
}
