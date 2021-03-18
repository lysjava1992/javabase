package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.util.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName IndexController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/2/9 11:53
 * @Version 1.0
 **/
@Api(value = "首页",tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首尔轮播图列表",notes = "获取首尔轮播图列表",httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carousel(){
        return IMOOCJSONResult.ok(carouselService.queryAll(YesOrNo.YES.type));
    }

    @ApiOperation(value = "商品分类（一级）",notes = "商品分类（一级）",httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats(){
        return IMOOCJSONResult.ok(categoryService.queryRootLevelCat());
    }
    @ApiOperation(value = "商品子分类",notes = "商品子分类",httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult getCatList(
            @ApiParam(name = "rootCatId" ,value = "一级分类ID",required = true)
            @PathVariable Integer rootCatId
            ){
        if(rootCatId==null){
            return IMOOCJSONResult.errorMsg("分类不存在");
        }
        return IMOOCJSONResult.ok(categoryService.getSubCatList(rootCatId));
    }
}
