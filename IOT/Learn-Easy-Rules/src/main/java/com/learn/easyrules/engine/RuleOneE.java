package com.learn.easyrules.engine;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

/**
 *  Rule 有三个属性
 *    name :规则名称 ，有默认值
 *    description：描述
 *    priority：可设置优先级
 */
@Rule(priority = 1)
public class RuleOneE {

    @Condition
    public boolean isOk(){

        return false;
    }


    @Action()
    public void do1(){
        System.out.println("RuleOneE触发");
        throw new RuntimeException("引擎异常");

    }


}
