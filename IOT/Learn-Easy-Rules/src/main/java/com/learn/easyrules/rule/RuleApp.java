package com.learn.easyrules.rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;

public class RuleApp {
    public static void main(String[] args) {
        Rules rules=new Rules();
        rules.register(new RuleOne());
        rules.register(new RuleTwo());

        Facts facts=new Facts();
        facts.put("key",new Object());
        RulesEngine rulesEngine=new DefaultRulesEngine();
        // RulesEngine rulesEngine=new InferenceRulesEngine();
        rulesEngine.fire(rules,facts);
    }
}
