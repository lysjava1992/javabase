package com.learn.test.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockTest2 {
    @Mock
    DefalutMachine machine;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test1(){
        // 执行的是mock对象而不是
        machine.produce();
        System.out.println(machine.produce(""));
    }
}
