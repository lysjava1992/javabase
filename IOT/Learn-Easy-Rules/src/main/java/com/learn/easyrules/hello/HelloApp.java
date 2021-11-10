package com.learn.easyrules.hello;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

public class HelloApp {
    public static void main(String[] args) {
        // 定义事实 即触发条件
        Facts facts=new Facts();

        //创建注册规则
        Rules rules=new Rules();
        rules.register(new HelloWorldRule());

        //创建引擎触发
        RulesEngine rulesEngine=new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);


    }
}
