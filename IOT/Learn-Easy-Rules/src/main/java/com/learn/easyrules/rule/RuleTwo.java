package com.learn.easyrules.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule
public class RuleTwo {


    @Condition
    public boolean isOk(Facts fact){
        System.out.println((Object) fact.get("key"));
        return true;
    }

    @Action(order = 1)
    public void do1( Facts fact){
        System.out.println(fact);
        System.out.println("规则[RuleTwo]执行1");
    }
    @Action(order = 2)
    public void do2(){
        System.out.println("规则[RuleTwo]-执行1");
    }


}
