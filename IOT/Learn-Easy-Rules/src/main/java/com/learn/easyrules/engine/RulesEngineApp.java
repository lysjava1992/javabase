package com.learn.easyrules.engine;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;

public class RulesEngineApp {
    public static void main(String[] args) {
        RulesEngineParameters parameters = new RulesEngineParameters()
                // 规则的优先级阙值。优先级>10的规则将失效
                .priorityThreshold(10)
                // 当多个规则符合时，只按优先级触发第一个
                // 默认 false全部触发
                .skipOnFirstAppliedRule(true)

                // 当有规则执行失败时是否跳过
                // 默认 false ,失败会忽略，执行其他的
                // true,则立即终止该轮推理
                .skipOnFirstFailedRule(true)

                // 多个规则，按优先级
                // 优先级最大的规则若没有被触发
                // 则后续的规则不会再推理
                // 只适用于第一轮
                . skipOnFirstNonTriggeredRule( true )
                ;

        Rules rules=new Rules();
        rules.register(new RuleOneE());
        rules.register(new RuleTwoE());
        rules.register(new RuleThreeE());

        //    RulesEngine rulesEngine=new DefaultRulesEngine(parameters);
        RulesEngine rulesEngine=new InferenceRulesEngine(parameters);
        rulesEngine.fire(rules,new Facts());
    }
}
