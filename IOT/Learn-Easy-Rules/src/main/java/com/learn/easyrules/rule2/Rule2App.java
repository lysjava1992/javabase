package com.learn.easyrules.rule2;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

public class Rule2App {
    public static void main(String[] args) {
        Rule rule=new RuleBuilder()
                .name("rule1")
                .priority(1)
                .description("描述")
                .when(new ConditionsOne())
                .then(new ActionOne())
                .then(new ActionTwo())
                .build();
        Rules rules=new Rules();
        rules.register(rule);
        Facts facts=new Facts();
        facts.put("number",5);
        RulesEngine rulesEngine=new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);
    }
}
