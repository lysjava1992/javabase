package com.handbook.java.spi;

import com.handbook.java.spi.service.Animal;

import java.util.ServiceLoader;

/**
 * @description:
 *        SPI【Service Provider Interface】 JDK内置的一种服务发现机制：
 *                  提供给服务厂商与扩展框架功能的开发者使用的接口
 *       比较常见的例子：
 *                 数据库驱动：JDBC加载不同类型数据库的驱动
 *                  日志：SLF4J加载不同提供商的日志实现类
 *                 Spring：如对servlet3.0规范对ServletContainerInitializer的实现、
 *                        自动类型转换Type Conversion SPI(Converter SPI、Formatter SPI)等
 *                Dubbo ：允许用户扩展实现Filter接口
 *  ============================使用必须遵守========================
 *   当服务提供者提供了接口的一种具体实现后,在jar包的 META-INF/services 目录下
 *              创建一个以"接口权限定名"为命名的文件，内容为实现类的权限定名；
 *
 *   接口实现类所在的jar包放在主程序的classpath中；
 *
 *   接口实现类必须带有一个无参构造方法；
 *
 *   主程序通过java.util.ServiceLoader动态加载META-INF中的实现类；
 *  ============================使用必须遵守========================
 *
 * @author: Mr.Luan
 * @create: 2019-11-05 15:47
 **/
public class AppStart {
    public static void main(String[] args) {
        ServiceLoader<Animal> animals=ServiceLoader.load(Animal.class);
        for(Animal a:animals){
                a.eat();
        }
    }
}
