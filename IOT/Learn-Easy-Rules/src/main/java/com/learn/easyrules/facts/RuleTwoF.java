package com.learn.easyrules.facts;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 *  Rule 有三个属性
 *    name :规则名称 ，有默认值
 *    description：描述
 *    priority：可设置优先级
 */
@Rule()
public class RuleTwoF {

    @Condition
    public boolean isOk(Facts facts){
        int number=facts.get("number");
        return number==1;
    }

    @Action()
    public void do1(){
        System.out.println("RuleTwo触发");
    }

}
