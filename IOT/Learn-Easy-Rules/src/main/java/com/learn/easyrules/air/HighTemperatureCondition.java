package com.learn.easyrules.air;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;

/**
 * 触发条件
 */
public class HighTemperatureCondition implements Condition {
    @Override
    public boolean evaluate(Facts facts) {
        Integer temperature=facts.get("temperature");
        return temperature>12;
    }

    static HighTemperatureCondition itIsHot() {
        return new HighTemperatureCondition();
    }

}
