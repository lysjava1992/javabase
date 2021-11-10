package com.learn.easyrules.rule2;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;

public class ActionOne implements Action {
    @Override
    public void execute(Facts facts) throws Exception {
        System.out.println("执行动作ActionOne");
    }
}
