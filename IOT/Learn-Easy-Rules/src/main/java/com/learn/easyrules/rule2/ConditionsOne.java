package com.learn.easyrules.rule2;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;

public class ConditionsOne implements Condition {

    @Override
    public boolean evaluate(Facts facts) {
        return true;
    }
}
