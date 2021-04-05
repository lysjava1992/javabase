package com.learn.spring.base.chapter25;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter25Config
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/4/3 10:56
 * @Version 1.0
 **/
@EnableLoadTimeWeaving
@EnableAspectJAutoProxy
@ComponentScan("com.learn.spring.base.chapter25")
@Configuration
public class Chapter25Config {

}
