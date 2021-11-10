package com.learn.easyrules.fizzbuzz;

import org.jeasy.rules.annotation.*;

@Rule()
public class BuzzRule {
    /**
     *  触发条件
     * @param number  fact 事实
     * @return  boolean
     */
    @Condition
    public  boolean isFizz(@Fact("number")Integer number){

        return number%7==0;
    }

    /**
     * 动作
     */
    @Action
    public void printFizz(){
        System.out.println("buzz");
    }

    /**
     *  优先级
     * @return int 
     */
    @Priority
    public int getPriority(){
        return 2;
    }
}
