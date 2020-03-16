package com.handbook.java.designmode.proxy;

import com.handbook.java.designmode.proxy.custom.CustomAgent;
import com.handbook.java.designmode.proxy.dynamic.cglib.CglibAgent;
import com.handbook.java.designmode.proxy.dynamic.jdk.JdkAgent;
import com.handbook.java.designmode.proxy.statical.HouseAgent;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *
 *  * 字节码重组
 *  1.拿到被代理对象的引用，并且获取到它的所有接口，反射获取
 *  2.JDK Proxy类重新生成一个新的类、同时新的类要实现被代理类所有实现的接口
 *  3.动态生成Java代码 ，把新加的业务逻辑方法由一定的逻辑代码去钓友
 *  4.编译生成新的Java代码 .class
 *  5.再重新加载到JVM中运行
 *
 * 用代码来实现在运行中编写 java代码， 再编译代码 最后加载到JVM
 *
 * @ClassName ProxyTest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 9:23
 * @Version 1.0
 **/
public class ProxyTest {
    public static void main(String[] args) throws IOException {
        // 静态代理  Agent只能对Person已经存在的行为进行代理，对Person未知的行为无法代理
        Doctor doctor=new Doctor();
        HouseAgent houseAgent=new HouseAgent(doctor);
        houseAgent.BuyHouse();


        //jdk 动态代理   当Person有新得行为时，Jdk一样可以代理；
        JdkAgent agent=new JdkAgent();
        //com.sun.proxy.$Proxy0
        Person person= (Person) agent.getInstance(doctor);
        System.out.println(person.getClass());
//        byte[] bytes= ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
//        FileOutputStream os=new FileOutputStream("E:\\JavaCode\\LearnCode\\Handbook-Java\\src\\main\\java\\com\\handbook\\java\\designmode\\proxy\\dynamic\\jdk\\$Proxy0.class");
//        os.write(bytes);
//        os.close();
        person.buyHouse();
        person.buyRice();

        //Cglib 动态代理  doctor1是继承于Doctor的一个子类 在运行过程中动态生成
        CglibAgent cglibAgent=new CglibAgent();
        //com.handbook.java.designmode.singleton.proxy.Doctor$$EnhancerByCGLIB$$2ea4b4e8
        Doctor doctor1= (Doctor) cglibAgent.getInstance(doctor);
        System.out.println(doctor1.getClass());
        doctor1.buyHouse();
        doctor1.buyRice();

        CustomAgent customAgent=new CustomAgent();
        Person person1= (Person) customAgent.getInstance(doctor);
        person1.buyHouse();
    }
}
