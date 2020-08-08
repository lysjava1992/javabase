package com.learn.springboot.chapter12.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine(id="orderMachine")
public class OrderSingleEvent {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @OnTransition(target = "UNPAID")
    public void  create(){
          logger.info("--订单创建，待支付--");
    }

    @OnTransition(source = "UNPAID",target = "WAITING_RECEIVE")
    public void  pay(){
        logger.info("--支付完毕，等待收货--");
    }

    @OnTransition(source ="WAITING_RECEIVE",target = "DONE")
    public void receive(){
        logger.info("--签收，订单完成--");
    }
}
