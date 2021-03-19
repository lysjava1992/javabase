package com.imooc.service.impl;

import com.imooc.mapper.CategoryMapper;
import com.imooc.mapper.CategoryMapperCustom;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.SubCategoryVo;
import com.imooc.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/2/9 12:14
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;
    @Override
    public List<Category> queryRootLevelCat() {
        Example example=new Example(Category.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("type",1);
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<SubCategoryVo> getSubCatList(Integer rootId) {
        return categoryMapperCustom.getSubCatList(rootId);
    }
}
