package com.handbook.java.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @description:
 *    java8 添加了工具包java.util.function 包含很多类来支持java函数式编程
 *    name	type	description
 *    Consumer	Consumer< T >	接收T对象，不返回值
 *    Predicate	Predicate< T >	接收T对象并返回boolean
 *    Function	Function< T, R >	接收T对象，返回R对象
 *    Supplier	Supplier< T >	提供T对象（例如工厂），不接收值
 *    UnaryOperator	UnaryOperator< T >	接收T对象，返回T对象
 *    BiConsumer	BiConsumer<T, U>	接收T对象和U对象，不返回值
 *    BiPredicate	BiPredicate<T, U>	接收T对象和U对象，返回boolean
 *    BiFunction	BiFunction<T, U, R>	接收T对象和U对象，返回R对象
 *    BinaryOperator	BinaryOperator< T >	接收两个T对象，返回T对象
 *
 *     Consumer 接口  消费某个对象 不需要返回值
 *     会接受单个输入参数，并可能对参数值进行修改
 *
 *     list.forEach(Consumer<? super T> action)
 *     即对list进行过滤修改
 *
 *     Predicate 返回boolean 过滤修改
 *
 *     Function< T, R >  可接收T可返回指定类型R的返回值
 *     UnaryOperator 接收返回类型一致
 *
 *     Supplier 工厂方法提供对象
 * @author: Mr.Luan
 * @create: 2019-12-10 11:20
 **/
public class Chapter4 {
    public static void main(String[] args) {
        Consumer<String> consumer=s->{if(s.startsWith("J")){ System.out.println(s); }};
        System.out.println("=============");
        List<String> list= Arrays.asList("Java","Python","C#","C","Js");
        list.forEach(p->System.out.println(p));
        System.out.println("=============");
        list.forEach(System.out::println);
        System.out.println("=============");
        list.forEach(consumer);
        System.out.println("=============");


        Predicate<String> predicate=s->s.startsWith("J");
          filter(list,predicate);
        System.out.println("=============");
        Predicate<String> predicate1=s->s.startsWith("C");
        filter(list,predicate1);
        System.out.println("=============");

        Function<Integer,Integer> function=(n)->n+1;
         System.out.println(function.apply(1));

        Supplier<Person> supplier=()->new Person();
        System.out.println(supplier.get());
        System.out.println(supplier.get());
        Supplier<Person> supplier1=Person::new;
        System.out.println(supplier.get().getAge());
        System.out.println(supplier.get().getAge());
    }
   static void  filter(List<String>list,Predicate predicate){
        for (String s:list){
            if(predicate.test(s)){
             System.out.println(s);
            }
        }
   }
   static class Person{
        private int age;
        private String name;

       public Person() {
           Random random=new Random();
           this.age= random.nextInt(10);
           this.name="Mr."+this.age;
       }

       public int getAge() {
           return age;
       }

       public void setAge(int age) {
           this.age = age;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       @Override
       public String toString() {
           return "{" +
                   "age=" + age +
                   ", name='" + name + '\'' +
                   '}';
       }
   }
}
