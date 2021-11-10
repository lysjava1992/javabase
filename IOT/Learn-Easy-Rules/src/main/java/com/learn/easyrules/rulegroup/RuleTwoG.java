package com.learn.easyrules.rulegroup;

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
public class RuleTwoG {

    /**
     *  用于定义规则的触发条件
     *   必须为public
     *   必须返回boolean
     *   一个rule必须有且只有一个 @Condition 方法
     *      Facts facts用来获取事实
     *      Facts facts=new Facts();
     *       facts.put("key",Object);
     *
     * @return
     */
    @Condition
    public boolean isOk(Facts facts){
        int number=facts.get("number");
        return number%2==0;
    }

    /**
     * 触发的动作用
     *  @Action 来表示
     *   一个rule可以有多个action
     *   当多个action有先后顺序时
     *   可用order来控制
     *    order越小越优先
     */
    @Action
    public void do1(){
        System.out.println("规则[RuleTwo]执行1");
    }


    /**
     *   @Priority标注的方法
     *    必须返回一个int值
     *    来控制多个规则被激活时的执行顺序
     *    越小越优先
     *    也可以使用@Rule(priority=1)来控制
     * @return
     */
    @Priority
    public int priority(){
        return 2;
    }
}
