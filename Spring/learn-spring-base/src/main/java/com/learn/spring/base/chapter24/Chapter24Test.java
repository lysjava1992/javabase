package com.learn.spring.base.chapter24;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Chapter24Test {
    private ApplicationContext context;
    @Before
    public void init(){
        context=new AnnotationConfigApplicationContext(Chapter24Config.class);
    }
    @Test
    public void test1(){
          SomeService service1=context.getBean(SomeService.class);
        // service1.doWork();
        service1.doWork(1);
    }
    @Test
    public void test2(){
     int[] arr={1,1,2,3};
     int[] current=arr.clone();
     current[1]=current[1]*100;
     System.out.println(Arrays.toString(arr));
     System.out.println(Arrays.toString(current));
    }
}
