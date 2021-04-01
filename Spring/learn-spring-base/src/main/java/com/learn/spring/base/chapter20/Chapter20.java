package com.learn.spring.base.chapter20;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Chapter20 {
    public static void main(String[] args) {
      ApplicationContext context=new AnnotationConfigApplicationContext(JavaSpringConfigure.class);
      PersonService service=context.getBean(PersonService.class);
      System.out.println(service.save(new Person()));
    }
   void BeanWrapperTest(){
       BeanWrapper company = new BeanWrapperImpl(new Company());
       // set name属性
       company.setPropertyValue("name", "Some Company Inc.");
       // 设置属性
       PropertyValue value = new PropertyValue("name", "Some Company Inc.");
       company.setPropertyValue(value);
       // 设置泛型
       BeanWrapper jim = new BeanWrapperImpl(new Employee());
       jim.setPropertyValue("name", "Jim Stravinsky");
       company.setPropertyValue("managingDirector", jim.getWrappedInstance());
       // 获取属性
       Float salary = (Float) company.getPropertyValue("managingDirector.salary");
   }
}
