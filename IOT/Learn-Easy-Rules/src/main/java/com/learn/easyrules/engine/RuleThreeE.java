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
@Rule(priority = 3)
public class RuleThreeE {


    @Condition
    public boolean isOk(@Fact("number") int number){
        return number>=2&&number<10;
        // return true;
    }


    @Action()
    public void do1(Facts facts){
        int number=facts.get("number");
        facts.put("number",number+1);
        System.out.println("RuleThreeE触发");


    }


}
