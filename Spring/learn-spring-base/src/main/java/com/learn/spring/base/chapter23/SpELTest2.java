package com.learn.spring.base.chapter23;

import com.learn.spring.base.chapter11.Parts;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.util.*;

public class SpELTest2 {
    private ExpressionParser parser;

    @Before
    public void init() {
        parser = new SpelExpressionParser();
    }

    @Test
    public void test() {
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().withRootObject(new Inventor("Tom", new Date(), "")).build();
        int year = (Integer) parser.parseExpression("Birthdate.Year + 1900").getValue(context);
        System.out.println(year);
    }

    @Test
    public void test1() {
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        Integer numbers = (Integer) parser.parseExpression("{1,2,3,4}[0]").getValue(context);
        System.out.println(numbers);
    }

    @Test
    public void test2() {
        Class dateClass = parser.parseExpression("T(java.util.Date)").getValue(Class.class);
        Class stringClass = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(parser.parseExpression(
                "T(java.lang.Math).random()*100")
                .getValue());

    }

    @Test
    public void test3() {
        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");

        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        context.setVariable("newName", "Mike Tesla");

        parser.parseExpression("Name = #newName").getValue(context, tesla);
        System.out.println(tesla.getName());
        System.out.println(parser.parseExpression("name").getValue(tesla));
    }

    @Test
    public void test4() {
        List<Integer> primes = new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        // create parser and set variable 'primes' as the array of integers
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("primes", primes);
        // all prime numbers > 10 from the list (using selection ?{...})
        // evaluates to [11, 13, 17]
        List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression(
                "#primes.?[#this>10]").getValue(context);
        System.out.println(primesGreaterThanTen);
    }

    @Test
    public void test5() throws NoSuchMethodException {
        Method method = StringUtils.class.getMethod("reverseString", String.class);
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("reverse", method);
        ExpressionParser parser = new SpelExpressionParser();
        String s = parser.parseExpression(" #reverse('HELLO')").getValue(context, String.class);
        System.out.println(s);
    }

    @Test
    public void test6() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new MyBeanResolver());
        Object name = parser.parseExpression("@name").getValue(context);
        System.out.println(name);
        System.out.println(parser.parseExpression("&foo").getValue(context));
    }

    @Test
    public void test7() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        tesla.setPlaceOfBirth(new PlaceOfBirth("Smiljan"));

        String city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, tesla, String.class);
        System.out.println(city);  // Smiljan

        tesla.setPlaceOfBirth(null);
        city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, tesla, String.class);
        System.out.println(city);  // null - does not throw NullPointerException!!!
    }

    @Test
    public void test8() {
        List<Inventor> list = new ArrayList<Inventor>();
        list.add(new Inventor("Nikola Tesla", "Serbian1"));
        list.add(new Inventor(" Tesla", "Serbian2"));

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("list", list);
        List placesOfBirth = (List) parser.parseExpression("#list.![#this.name]").getValue(context);
        System.out.println(placesOfBirth);
    }

    @Test
    public void test9() {
        //2.测试字典
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key4", 4);
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("map", map);
        List<Integer> list = (List<Integer>) parser.parseExpression("#map.![value+1]").getValue(context);
        System.out.println(list);
    }

    @Test
    public void test11() {
//创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();
//创建解析器上下文
        ParserContext context = new TemplateParserContext("%{", "}");
        Expression expression = parser.parseExpression("你好:%{#name},我们正在学习:%{#lesson}", context);
//创建表达式计算上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("name", "Tom");
        evaluationContext.setVariable("lesson", "spring高手系列!");
        //获取值
        String value = expression.getValue(evaluationContext, String.class);
        System.out.println(value);
    }
}
