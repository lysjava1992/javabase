package com.imooc.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ServiceLogAspect
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/12/20 18:17
 * @Version 1.0
 **/
@Aspect
@Component
public class ServiceLogAspect {
    public static Logger log=
            LoggerFactory.getLogger(ServiceLogAspect.class);
   @Around("execution(* com.imooc.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("开始执行{}-{}",joinPoint.getTarget().getClass(),
                 joinPoint.getSignature().getName());
        long begin=System.currentTimeMillis();
        Object object=joinPoint.proceed();
        long end=System.currentTimeMillis();

        long takeTime=end-begin;
        if(takeTime>3000){
            log.error("执行结束：耗时【{}】毫秒",takeTime);
        }else if(takeTime>2000){
            log.warn("执行结束：耗时【{}】毫秒",takeTime);
        }else {
            log.info("执行结束：耗时【{}】毫秒",takeTime);
        }
        return object;
    }








}
