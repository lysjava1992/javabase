package com.learn.test.mock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * mock对象就是在调试期间用来作为真实对象的替代品。
 * mock测试就是在测试过程中，
 * 对那些不容易构建的对象用一个虚拟对象来代替测试的方法就叫mock测试。
 * 创建mock对象不能对final，Anonymous ，private 类进行mock。
 */
public class MockTest1 {

    @Test
    public void simpleTest() {
        //创建mock对象，参数可以是类，也可以是接口
        List<String> list = mock(List.class);
        //设置方法的预期返回值
        // 当调用list的get方法时 参数为0 返回值为"helloworld"
        //                       参数为1 返回值为"5"
        when(list.get(0)).thenReturn("helloworld");
        when(list.get(5)).thenReturn("5");
        //调用
        String result = list.get(0);
        //验证方法调用(是否调用了get(0))
        verify(list).get(0);
        //junit测试 断言确认
        Assert.assertEquals("helloworld", result);
    }

    @Test
    public void simpleTest2() {
        Machine machine = mock(Machine.class);
        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                return "called with arguments: " + args;
            }
        }).when(machine).produce();
        when(machine.produce("str")).thenReturn("OK");
        doReturn("HELLO").when(machine).produce(anyString());
        when(machine.produce2()).thenThrow(new RuntimeException("调用异常"));
        //  machine.produce();
        System.out.println(machine.produce("str"));
        ;
        //   machine.produce2();
    }

    @Test
    public void argumentMatcherTest() {
        List<String> list = mock(List.class);
        when(list.get(anyInt())).thenReturn("Hello", "world");
        String result = list.get(0) + list.get(1);
        verify(list, times(2)).get(anyInt());
        Assert.assertEquals("Helloworld", result);

    }

    @Test
    public void argumentMatcherTest2() {
        Machine machine = mock(Machine.class);
        doReturn("OK").when(machine).produce(anyString(), anyInt());
        Assert.assertEquals("OK", machine.produce("OK", 20));
        verify(machine).produce("OK", 20);
        verify(machine).produce(anyString(), anyInt());
        verify(machine).produce(eq("OK"), eq(20));
    }

    @Test
    public void returnMatcherTest2() {
        Machine machine = mock(Machine.class);
        when(machine.produce("str")).thenReturn("OK", "OK2", "OK3");
        System.out.println(machine.produce("str"));
        System.out.println(machine.produce("str"));
        System.out.println(machine.produce("str"));
        System.out.println(machine.produce("str"));
    }

    @Test
    public void verifyInvocate() {
        List<Integer> list = mock(List.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(4);
        /**
         * 基本的验证方法
         * verify方法验证mock对象是否有没有调用mockedList.add("once")方法
         * 不关心其是否有返回值，如果没有调用测试失败。
         */
        verify(list).add(1);
        verify(list, times(1)).add(2);//默认调用一次,times(1)可以省略
        verify(list, times(4)).add(4);
        verify(list, times(7)).add(anyInt());
        //never()等同于time(0),一次也没有调用
        verify(list, times(0)).add(10);
        //atLeastOnece/atLeast()/atMost()
        verify(list, atLeastOnce()).add(2);
        verify(list, atLeast(1)).add(2);
        verify(list, atMost(5)).add(2);
    }

    /**
     * 参数校验
     */
    @Test
    public void argumentCaptorTest() {
        List<String> mock = mock(List.class);
        List<String> mock2 = mock(List.class);
        mock.add("Tom");
        mock2.add("Jon");
        mock2.add("Brain");
        // 参数破获器
        ArgumentCaptor argumentCaptor = ArgumentCaptor.forClass(String.class);
        //capture()开启参数捕获
        verify(mock).add((String) argumentCaptor.capture());
        Assert.assertEquals("Tom", argumentCaptor.getValue());

        verify(mock2, times(2)).add((String) argumentCaptor.capture());
        //getValue() 获取参数 最后一次执行的参数
        Assert.assertEquals("Brain", argumentCaptor.getValue());

        //getAllValues() 捕获的所有参数
        System.out.println(Arrays.toString(argumentCaptor.getAllValues().toArray()));
        Assert.assertArrayEquals(new Object[]{"Tom", "Jon", "Brain"}, argumentCaptor.getAllValues().toArray());

    }

    @Test
    public void test() {
        Machine machine = mock(Machine.class);
        when(machine.produce(anyString()))
                .thenReturn("1", "2")
                .thenThrow(new RuntimeException("参数异常"));

    }

    @Test
    public void spyTest() {
        List<Integer> list=mock(List.class);
        List<Integer> spyList=spy(list);
        when(spyList.size()).thenReturn(100);

    }
}
