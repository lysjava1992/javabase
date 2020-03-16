package com.handbook.java.designmode.factory.simple;

import com.handbook.java.designmode.factory.GuangMing;
import com.handbook.java.designmode.factory.MengNiu;
import com.handbook.java.designmode.factory.Milk;
import com.handbook.java.designmode.factory.YiLi;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SimpleFactory
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 11:26
 * @Version 1.0
 **/
public class SimpleFactory {
    public static String MIKE_MENGNIU="蒙牛";
    public static String MIKE_YILI="伊利";
    public static String MIKE_GUANGMING="光明";
    public Milk getMike(String name){
         if(name.equals(MIKE_MENGNIU)){
             return new MengNiu();
         }else if(name.equals(MIKE_YILI)){
             return new YiLi();
         }else if(name.equals(MIKE_GUANGMING)){
             return new GuangMing();
         }else {
             return null;
         }
    }
}
