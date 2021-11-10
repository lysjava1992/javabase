package com.learn.easyrules.air;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

import static com.learn.easyrules.air.DecreaseTemperatureAction.decreaseTemperature;
import static com.learn.easyrules.air.HighTemperatureCondition.itIsHot;

public class AirApp {
    public static void main(String[] args) throws InterruptedException {
        Rule airConditioningRule=new RuleBuilder()
                .when(itIsHot())
                .then(decreaseTemperature())
                .build();
        Facts facts=new Facts();
        facts.put("temperature",30);

        Rules rules=new Rules();
        rules.register(airConditioningRule);


     //   new DefaultRulesEngine().fire(rules,facts); 只会推理触发一次
        // InferenceRulesEngine会不断推理，直到没有符合的为止
        RulesEngine rulesEngine = new InferenceRulesEngine();
        rulesEngine.fire(rules, facts);
        System.out.println((int) facts.get("temperature"));

    }
}
