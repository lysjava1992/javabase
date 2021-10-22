package com.learn.springboot.chapter12.statemachine;

import com.learn.springboot.chapter12.enums.Events;
import com.learn.springboot.chapter12.enums.States;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class OrderStateMachineBuilder {
    private final static String MACHINEID = "orderMachine";
    /**
     * 构建状态机
     *
     * @param beanFactory
     * @return
     * @throws Exception
     */
    public StateMachine<States, Events> build(BeanFactory beanFactory) throws Exception {
        StateMachineBuilder.Builder<States, Events> builder = StateMachineBuilder.builder();
        builder.configureConfiguration()
                .withConfiguration()
                .machineId(MACHINEID)
                .beanFactory(beanFactory);
        builder.configureStates()
                .withStates()
                .initial(States.UNPAID)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.UNPAID).target(States.WAITING_RECEIVE)
                .event(Events.PAY).action(action())
                .and()
                .withExternal()
                .source(States.WAITING_RECEIVE).target(States.DONE)
                .event(Events.RECEIVE);

        return builder.build();
    }

    @Bean
    public Action<States, Events> action() {
        return new Action<States, Events>() {
            @Override
            public void execute(StateContext<States, Events> stateContext) {
                System.out.println(stateContext);
            }
        };
    }
}
