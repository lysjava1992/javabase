package com.imooc.service.impl;

import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.service.CarouselService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;
import tk.mybatis.mapper.entity.Example;

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
@Service
class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example=new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("isShow",isShow);
        return carouselMapper.selectByExample(example);
    }
}
