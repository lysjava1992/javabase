package com.handbook.java.designmode.decorator;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *     具体实现类
 *      被装饰者 具有核心完整的功能
 * @ClassName ConcretComponent
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/26 11:00
 * @Version 1.0
 **/
public class ConcretComponent implements Component {
    @Override
    public void doThing() {
          System.out.println("核心功能.............");
    }
}
