package com.imooc.mapper;

import com.imooc.base.mapper.BaseMapper;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.SubCategoryVo;

import java.util.List;

public interface CategoryMapperCustom  {
    public List<SubCategoryVo> getSubCatList(Integer rootCatId);
}