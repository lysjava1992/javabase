package com.learn.easyrules.air;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;

/**
 * 触发的动作
 */
public class DecreaseTemperatureAction implements Action {
    @Override
    public void execute(Facts facts) throws Exception {
         System.out.println("It is hot! cooling air...");
         Integer temperature=facts.get("temperature");
         facts.put("temperature",temperature-1);
    }

    static DecreaseTemperatureAction decreaseTemperature() {
        return new DecreaseTemperatureAction();
    }
}
