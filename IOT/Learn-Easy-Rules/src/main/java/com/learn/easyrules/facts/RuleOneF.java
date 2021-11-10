package com.learn.easyrules.facts;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

/**
 *  Rule 有三个属性
 *    name :规则名称 ，有默认值
 *    description：描述
 *    priority：可设置优先级
 */
@Rule()
public class RuleOneF {

    @Condition
    public boolean isOk(@Fact("str") String str){
        System.out.println("RuleOneF "+str);
        return "OK".equals(str);
    }


    @Action()
    public void do1(){
        System.out.println("RuleOneF触发");
    }


}
