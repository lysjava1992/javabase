package com.learn.springboot.chapter12;

import com.learn.springboot.chapter12.enums.Events;
import com.learn.springboot.chapter12.enums.States;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;

@SpringBootApplication
public class LearnSpringBootChapter12Application {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(LearnSpringBootChapter12Application.class, args);

	}

}
