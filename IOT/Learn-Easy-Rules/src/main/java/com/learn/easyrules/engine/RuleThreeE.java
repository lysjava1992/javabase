package com.learn.easyrules.engine;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;

/**
 *  Rule 有三个属性
 *    name :规则名称 ，有默认值
 *    description：描述
 *    priority：可设置优先级
 */
@Rule(priority = 3)
public class RuleThreeE {

    @Condition
    public boolean isOk(){

        return true;
    }


    @Action()
    public void do1(){
        System.out.println("RuleThreeE触发");
    }


}
