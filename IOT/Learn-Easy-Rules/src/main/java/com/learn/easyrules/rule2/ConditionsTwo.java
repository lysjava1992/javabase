package com.learn.easyrules.rule2;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;

public class ConditionsTwo implements Condition {
    
    @Override
    public boolean evaluate(Facts facts) {
        int number=facts.get("number");
        return number%5==0;
    }
}
