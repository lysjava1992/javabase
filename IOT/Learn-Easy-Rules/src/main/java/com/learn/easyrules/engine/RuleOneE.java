package com.learn.easyrules.engine;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 *  Rule 有三个属性
 *    name :规则名称 ，有默认值
 *    description：描述
 *    priority：可设置优先级
 */
@Rule(priority = 1)
public class RuleOneE {

    @Condition
    public boolean isOk(@Fact("number") int number){

        // return true;
        return number==0;
    }


    @Action()
    public void do1(Facts facts){
         int number=facts.get("number");
         facts.put("number",number+1);
         System.out.println("RuleOneE触发");
        //throw new RuntimeException("引擎异常");

    }


}
