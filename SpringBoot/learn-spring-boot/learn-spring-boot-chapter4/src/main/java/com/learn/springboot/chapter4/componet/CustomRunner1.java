package com.learn.springboot.chapter4.componet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description:
 *   @Order()
 *    用来指定多个任务时 任务的优先级
 *    int  越小 优先级越大
 *
 *     启动时传参
 *    java -jar appName.jar  value1  value2
 * @author: Mr.Luan
 * @create: 2020-03-26 14:39
 **/
@Component
@Order(100)
public class CustomRunner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        if(args!=null){
            for (String str:args){
                System.out.println(str);
            }
        }
        System.out.println("====================【CustomRunner1】任务执行优先级100");
    }
}
