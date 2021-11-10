package com.learn.easyrules.hello;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

/**
 * 定义
 */
@Rule(name = "Hello World rule" ,description = "Always sqy hello world")
public class HelloWorldRule {
    /**
     *  触发条件
     * @return 返回 true :触发
     *               false :不触发
     */
    @Condition
    public boolean when(){
        return true;
    }

    @Action
    public void then(){
        System.out.println("hello world");
    }


}
