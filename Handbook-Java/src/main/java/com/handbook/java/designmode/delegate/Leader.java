package com.handbook.java.designmode.delegate;

import com.handbook.java.designmode.delegate.impl.AfterEnd;
import com.handbook.java.designmode.delegate.impl.ForeEnd;
import com.handbook.java.designmode.delegate.impl.Stylist;

import java.util.HashMap;
import java.util.Map;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Leader
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/24 10:56
 * @Version 1.0
 **/
public class Leader {
    private static Map<String,Clerk> map=new HashMap<>();
    public Leader() {
        map.put("design",new Stylist());
        map.put("html",new ForeEnd());
        map.put("java",new AfterEnd());
    }


    public void doing(String command) {
           map.get(command).doWork();
    }
}
