package com.learn.drools.hello;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Order
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/11/8 21:20
 * @Version 1.0
 **/
@NoArgsConstructor
@Data
public class Order {
   private String id;
   private String name;
   private Double initialPrice;
   private Double pricePaid;
   private List<String> goods;
    public Order(Double initialPrice) {
        this.id= UUID.randomUUID().toString();
        this.initialPrice = initialPrice;
    }
}
