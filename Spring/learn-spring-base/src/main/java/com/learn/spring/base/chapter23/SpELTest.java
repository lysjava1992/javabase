package com.learn.spring.base.chapter23;

import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.GregorianCalendar;

public class SpELTest {
    private   ExpressionParser parser ;
    @Before
    public void init(){
        parser = new SpelExpressionParser();
    }
    @Test
    public void test(){

        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
        System.out.println(message);
    }
    @Test
    public void test1(){

        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        System.out.println(message);
    }

    @Test
    public void test2(){

        Expression exp = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        System.out.println(bytes );
    }
    @Test
    public void test3(){
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);
      // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");
        Expression exp = parser.parseExpression("name");
        String name = (String) exp.getValue(tesla);
       // name == "Nikola Tesla"
        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
      // result == true

    }
    @Test
    public void test4(){
        Simple simple=new Simple();
        simple.booleanList.add(true);
        EvaluationContext context= SimpleEvaluationContext.forReadOnlyDataBinding().build();
        //parser.parseExpression("booleanList[0]").setValue(context, simple, "false");
        Expression exp = parser.parseExpression("booleanList[0]");
        System.out.println(exp.getValue(simple));
         exp.setValue(context,simple, "false");
        Boolean b = simple.booleanList.get(0);
        System.out.println(b);
    }
    @Test
    public void test5(){}
    @Test
    public void test6(){}
}
