package com.learn.spring.base.chapter22;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;


@Service
public class TestBeanService {
    @Autowired
    private SmartValidator smartValidator;
    void testBean(  TestBean bean){
        Errors errors = new DirectFieldBindingResult( bean, "bean");
        ValidationUtils.invokeValidator(smartValidator, bean, errors);
        System.out.println(errors);

    }
}
