package com.learn.spring.base.chapter20;
import com.learn.spring.base.chapter20.beanWrapper.Company;
import com.learn.spring.base.chapter20.beanWrapper.Employee;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Chapter20 {
    public static void main(String[] args) {
      ApplicationContext context=new AnnotationConfigApplicationContext(JavaSpringConfigure.class);
      PersonService service=context.getBean(PersonService.class);
      Person person=new Person();
      person.setAge(-5);
      System.out.println(service.save(person));
    }

}
