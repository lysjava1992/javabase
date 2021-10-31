package com.learn.spring.base.chapter20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class PersonService {


    public Person save( Person person){
        PersonValidator validator = new PersonValidator();
        BindException errors = new BindException(person, "person");
        validator.validate(person,errors);

        if(errors.hasErrors()){
            System.out.println(errors.getMessage());
        }
        return person;
    }
}
