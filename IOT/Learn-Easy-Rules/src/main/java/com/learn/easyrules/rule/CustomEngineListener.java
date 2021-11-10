package com.learn.easyrules.rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineListener;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CustomEngineListener
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/11/10 22:28
 * @Version 1.0
 **/
public class CustomEngineListener implements RulesEngineListener {

    /**
     *    DefaultRulesEngine 时
     *    只触发一次
     *
     *   InferenceRulesEngine 时，
     *   每一轮推理之前都会触发
     * @param rules 所有规则
     * @param facts
     */
    @Override
    public void beforeEvaluate(Rules rules, Facts facts) {
         System.out.println("引擎监听[评估]: ");
    }

    /**
     *
     *   DefaultRulesEngine 时
     *    只在推理触发完毕时触发
     *
     *   InferenceRulesEngine 时，
     *    每一轮推理执行完毕之后都会触发
     * @param rules 所有规则
     * @param facts
     */
    @Override
    public void afterExecute(Rules rules, Facts facts) {
        System.out.println("引擎监听[执行完毕]: ");
    }
}
