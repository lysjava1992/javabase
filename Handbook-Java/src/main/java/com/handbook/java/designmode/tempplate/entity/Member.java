package com.handbook.java.designmode.tempplate.entity;

import lombok.Data;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Member
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 11:02
 * @Version 1.0
 **/
@Data
public class Member {
    private long id;
    private String name;
    private int age;
    private String addr;
}
