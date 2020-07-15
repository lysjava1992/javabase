package com.learn.springboot.chapter12;

import com.learn.springboot.chapter12.enums.Events;
import com.learn.springboot.chapter12.enums.States;
import com.learn.springboot.chapter12.statemachine.OrderStateMachineBuilder;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

@SpringBootTest
class LearnSpringBootChapter12ApplicationTests {
	@Autowired
	private StateMachine orderSingleMachine;

	@Autowired
	private OrderStateMachineBuilder machineBuilder;
	@Autowired
	private BeanFactory beanFactory;

	@Ignore
	@Test
	void stateMachineAlone() {
		// 创建流程
		orderSingleMachine.start();
		// 触发PAY事件
		orderSingleMachine.sendEvent(Events.PAY);
		// 触发RECEIVE事件
		orderSingleMachine.sendEvent(Events.RECEIVE);

	}
	@Test
	void stateMachines() throws Exception {
		for (int i=0;i<10;i++){
	                    new Thread(()->{
							StateMachine<States, Events> stateMachine = null;
							try {
								stateMachine= machineBuilder.build(beanFactory);
							} catch (Exception e) {
								e.printStackTrace();
							}
							// 创建流程
							// 创建流程
							stateMachine.start();
							// 触发PAY事件
							stateMachine.sendEvent(Events.PAY);
							// 触发RECEIVE事件
							stateMachine.sendEvent(Events.RECEIVE);
						}).start();
		}


	}
}
