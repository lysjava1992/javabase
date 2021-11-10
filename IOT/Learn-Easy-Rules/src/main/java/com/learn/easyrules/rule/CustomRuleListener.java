package com.learn.easyrules.rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.RuleListener;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CustomListener
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/11/10 22:15
 * @Version 1.0
 **/
public class CustomRuleListener implements RuleListener {

    /**
     * 在评估规则之前触发
     *   即判断该规则是否要触发
     *
     * @param rule
     * @param facts
     * @return false 则不再评估
     */
    @Override
    public boolean beforeEvaluate(Rule rule, Facts facts) {
        System.out.println("即将评估 "+rule.getName());
        return true;
    }

    /**
     *  评估完毕
     * @param rule
     * @param facts
     * @param evaluationResult 评估结果
     */
    @Override
    public void afterEvaluate(Rule rule, Facts facts, boolean evaluationResult) {
        System.out.println("规则  "+rule.getName()+"  是否触发"+evaluationResult);
    }


    @Override
    public void onEvaluationError(Rule rule, Facts facts, Exception exception) {
      System.out.println("评估时发生异常触发");
    }

    @Override
    public void beforeExecute(Rule rule, Facts facts) {
        System.out.println("即将执行触发的动作");
    }

    @Override
    public void onSuccess(Rule rule, Facts facts) {
        System.out.println("触发动作执行完毕");
    }

    @Override
    public void onFailure(Rule rule, Facts facts, Exception exception) {
        System.out.println("触发动作执行异常");
    }
}
