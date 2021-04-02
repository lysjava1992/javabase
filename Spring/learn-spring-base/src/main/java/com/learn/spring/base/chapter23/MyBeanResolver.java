package com.learn.spring.base.chapter23;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.AccessException;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.EvaluationContext;

public class MyBeanResolver implements BeanResolver {


    public Object resolve(EvaluationContext context, String beanName)
            throws AccessException {
        System.out.println(beanName);
        return new Inventor("Nikola Tesla", "Serbian");
    }

}
