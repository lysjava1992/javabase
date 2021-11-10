package com.learn.easyrules.fizzbuzz;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.support.composite.UnitRuleGroup;

/**
 * 规则合成
 *  只有组内所有规则都符合
 *  才会触发，触发会触发组内所有规则的动作
 *   此处使用 @Condition 和@Action 无效
 *    evaluate 遍历判断是否每个条件都符合
 *    execute  遍历执行每个自规则的动作
 */
public class FizzBuzzRule extends UnitRuleGroup {
    public FizzBuzzRule(Object... rules) {
        for (Object rule:rules){
            addRule(rule);
        }
    }

    @Override
    public void execute(Facts facts) throws Exception {
        super.execute(facts);
        System.out.println("----组合规则---");
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
