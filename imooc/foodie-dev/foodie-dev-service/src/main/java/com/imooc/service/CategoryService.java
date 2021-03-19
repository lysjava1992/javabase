package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.SubCategoryVo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CategoryService
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/2/9 12:14
 * @Version 1.0
 **/
public interface CategoryService {
     List<Category> queryRootLevelCat();

     List<SubCategoryVo> getSubCatList(Integer rootId);
}














