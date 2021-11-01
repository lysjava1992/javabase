package com.learn.spring.base.chapter23;

import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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


        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        //从bean中解析
        Expression exp = parser.parseExpression("name");
        String name = (String) exp.getValue(tesla);


       // name == "Nikola Tesla"
        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
      // result == true
    }

    /**
     * 了解评估上下文
     */
    @Test
    public void test4(){
        Simple simple=new Simple();
        simple.booleanList.add(true);
        EvaluationContext context= SimpleEvaluationContext.forReadOnlyDataBinding().build();
        //parser.parseExpression("booleanList[0]").setValue(context, simple, "false");
        Expression exp = parser.parseExpression("booleanList[0]");
        System.out.println(exp.getValue(simple));
        //setValue 需要通过context来感知simple 中‘booleanList[0]’的类型
        // 以及将value转换为对应的类型
        exp.setValue(context,simple, "false");
        Boolean b = simple.booleanList.get(0);
        System.out.println(b);
    }
    @Test
    public void test5(){
        SpelParserConfiguration config=new SpelParserConfiguration(true,true);
        ExpressionParser parser=new SpelExpressionParser(config);

        Expression expression=parser.parseExpression("strings[3]");
        Demo demo=new Demo();
        List<String> list=new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        demo.strings=list;
        Object o=expression.getValue(demo);

        System.out.println(o);
    }

}
