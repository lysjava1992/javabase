package com.learn.spring.base.chapter20;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {
    //是否验证
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }
     //验证逻辑
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name","name为空");
        Person p= (Person) target;
        if(p.getAge()<0){
            errors.rejectValue("age","非法年龄");
        }else if(p.getAge()>120){
            errors.rejectValue("age","are you sure");
        }
    }
}
