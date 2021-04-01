package com.learn.spring.base.chapter20;

import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;

@Service
public class PersonService {

    public Person save(@Validated Person person){
        return person;
    }
}
