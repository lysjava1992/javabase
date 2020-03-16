package com.learn.spring.base.chapter4.resources;

import com.learn.spring.base.chapter4.bean.Consumer;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName LoginRepository
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 11:11
 * @Version 1.0
 **/
@Repository
public class LoginRepository {
  public   Consumer find(String name){
            Consumer consumer=new Consumer();
            consumer.setName(name);
            consumer.setPassword(UUID.randomUUID().toString());
        return consumer;
    }
}
