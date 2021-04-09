package com.learn.spring.base.chapter25;

import org.springframework.stereotype.Service;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName StubEntitlementCalculationService
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/4/3 11:36
 * @Version 1.0
 **/
@Service
public class StubEntitlementCalculationService {
    public void calculateEntitlement() throws InterruptedException {
        Thread.sleep(50);
          System.out.println("----------");
    }
}
