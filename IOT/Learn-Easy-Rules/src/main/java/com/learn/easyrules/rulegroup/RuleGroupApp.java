package com.learn.easyrules.rulegroup;

import com.learn.easyrules.rule.RuleTwo;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.support.composite.ActivationRuleGroup;
import org.jeasy.rules.support.composite.ConditionalRuleGroup;
import org.jeasy.rules.support.composite.UnitRuleGroup;

public class RuleGroupApp {
    public static void main(String[] args) {
           // 该符合组内所有规则都符合则都触发
       // UnitRuleGroup group=new UnitRuleGroup();

          //当该组中只有一个rule符合则触发该rule
          //当该组中有多个rule符合按规则优先级仅触发执行优先级最高的一条
       // ActivationRuleGroup group=new ActivationRuleGroup();

          // 按规则优先级将优先级最高的规则条件作为主条件
          // 若主条件符合，则剩下的规则依次判断触发
          // 若主条件不负责，则不再推理，即有其他规则符合也不会触发
        ConditionalRuleGroup group=new ConditionalRuleGroup();
        group.addRule(new RuleOneG());
        group.addRule(new RuleTwoG());
        group.addRule(new RuleThreeG());
        Rules rules=new Rules();
        rules.register(group);


        Facts facts=new Facts();
        facts.put("number",0);
        RulesEngine rulesEngine=new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);
    }
}
