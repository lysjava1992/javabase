package com.learn.springboot.chapter12.statemachine;

import com.learn.springboot.chapter12.enums.Events;
import com.learn.springboot.chapter12.enums.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.*;
import org.springframework.statemachine.config.common.annotation.AnnotationBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;


/**
 * 状态机配置类
 */
@Configuration
@EnableStateMachine //启用状态机
public class OrderMachineConfig extends EnumStateMachineConfigurerAdapter<States,Events>{
    private Logger logger=  LoggerFactory.getLogger(getClass());

    /**
     *  初始化状态机状态
     *  初始状态  initial(States.UNPAID)
     *  所有状态  states(EnumSet.allOf(States.class));
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                 .initial(States.UNPAID)
                 .states(EnumSet.allOf(States.class));
    }

    /**
     * 初始化状态机状态变化
     *   .withExternal()
     *      .source(来源状态).target(目标状态)
     *      .event(触发事件)
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
             .withExternal()
                .source(States.UNPAID).target(States.WAITING_RECEIVE)
                .event(Events.PAY)
                .and()
             .withExternal()
                .source(States.WAITING_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE);
    }





}






















