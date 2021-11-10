package com.learn.easyrules.facts;

import com.learn.easyrules.rule.RuleTwo;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

public class FactsApp {
    public static void main(String[] args) {
        Rules rules=new Rules();
        rules.register(new RuleOneF());
        rules.register(new RuleTwoF());


       // Fact<String> fact = new Fact("str", "OK");

        Facts facts=new Facts();
       //  facts.put("number",1);
        facts.put("str","OK");
       // facts.add(fact);
        RulesEngine rulesEngine=new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);
    }
}
