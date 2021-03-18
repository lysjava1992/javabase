package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CarouselService
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/2/9 11:47
 * @Version 1.0
 **/
public interface  CarouselService {
    public List<Carousel> queryAll(Integer isShow);
}
