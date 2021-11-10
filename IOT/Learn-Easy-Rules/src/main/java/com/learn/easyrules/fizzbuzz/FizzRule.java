package com.learn.easyrules.fizzbuzz;

import org.jeasy.rules.annotation.*;
import org.omg.CORBA.PUBLIC_MEMBER;

@Rule()
public class FizzRule {
    /**
     *  触发条件
     * @param number  fact 事实
     * @return  boolean
     */
    @Condition
    public  boolean isFizz(@Fact("number")Integer number){

        return number%5==0;
    }

    /**
     * 动作
     */
    @Action
    public void printFizz(){
        System.out.println("fizz");
    }

    /**
     *  优先级
     * @return int
     */
    @Priority
    public int getPriority(){
        return 1;
    }
}
