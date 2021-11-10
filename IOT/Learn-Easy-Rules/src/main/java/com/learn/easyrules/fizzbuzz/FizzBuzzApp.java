package com.learn.easyrules.fizzbuzz;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

/**
 *  与drools不同 drools   优先级是 int 越大越有限
 *            而easyRules 同样用int    越小越优先
 *   drools中有继承规则的概念，但继承的只是LHS,执行动作不会
 *            而easyRules 使用组合的方式 所有条件都符合，所有规则的动作都执行
 *
 */
public class FizzBuzzApp {
    public static void main(String[] args) {
        // 当多个规则符合时，按规则优先级，只触发执行一个
        RulesEngineParameters parameters=new RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine rulesEngine=new DefaultRulesEngine(parameters);

        //创建规则
        Rules rules=new Rules();
        rules.register(new FizzRule());
        rules.register(new BuzzRule());
        rules.register(new FizzBuzzRule(new FizzRule(),new BuzzRule()));
        rules.register(new NonFizzBuzzRule());

        //触发
        Facts facts=new Facts();
      //  for (int i = 0; i <100 ; i++) {
            facts.put("number",35);
            rulesEngine.fire(rules,facts);
            System.out.println();
      //  }

    }
}
