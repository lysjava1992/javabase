package com.learn.easyrules.mvel;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MvelApp {
    public static void main(String[] args) throws Exception {
        // 闯进rules
        Person person = new Person("Tom", 14);
        Facts facts = new Facts();
        facts.put("person", person);

       Rule rule1=  processWithJava();
       Rule rule2=  processWithYml();
        Rules rules=new Rules();
        rules.register(rule1);
        rules.register(rule2);

        // 触发
        RulesEngine rulesEngine=new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);
    }

    /**
     *  基于java 编码创建MVEL规则
     * @return
     */
    private static Rule processWithJava() {
         Rule ageRule=new MVELRule()
                      .name("age_rule")
                      .description("如果年龄小于18则是未成年")
                      .priority(1)
                      .when("person.age > 18") //触发条件
                      .then("person.setAdult(true);"); //触发动作
         return ageRule;
    }

    /**
     * 基于yml文件配置
     * @return
     */
    private static Rule processWithYml() throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        String path=MvelApp.class.getClassLoader().getResource("rules/alcohol-rule.yml").getFile();
        System.out.println(path);
        Rule alcoholRule = ruleFactory.createRule(new FileReader(path));
       return alcoholRule;
    }


}
