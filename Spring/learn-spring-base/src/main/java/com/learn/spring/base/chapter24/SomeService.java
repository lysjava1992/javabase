package com.learn.spring.base.chapter24;


import java.util.HashMap;
import java.util.Map;

public interface SomeService {
    Map<String, String> map = new HashMap<String, String>();

    void doWork();

    void doWork(String value);

    void doWork(int value);

    void doWork(String type, int value);
}
