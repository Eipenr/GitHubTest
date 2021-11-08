package com.qianfeng.smartdevices.controller;


import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.annotation.AopLogAnnotation;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.pojo.Categories;
import com.qianfeng.smartdevices.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private CategoriesService categoriesService;

    @Autowired
    public void setCategoriesService(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/category")
    @AopLogAnnotation("添加分类")
    public R addCategories(@RequestBody Categories category) {
        categoriesService.addCategories(category);
        return R.setOK();
    }

    @GetMapping("/categories")
    @AopLogAnnotation("查找所有分类")
    public R findAllCategories(@RequestParam int page, @RequestParam int limit,@RequestParam(required = false) String categoryname,@RequestParam(required = false) Long status) {
        PageInfo<Categories> allCategories = categoriesService.findAllCategories(page, limit,categoryname,status);
        return R.setOK(allCategories);
    }

    @DeleteMapping("/categories")
    @AopLogAnnotation("删除分类")
    public R deleteCategories(@RequestParam List<Integer> ids) {
        categoriesService.deleteCategory(ids);
        return R.setOK();
    }

    @PutMapping("/category")
    @AopLogAnnotation("更新分类")
    public R updateCategory(@RequestBody Categories categories) {
        categoriesService.updateCategory(categories);
        return R.setOK();
    }

    @GetMapping("/detail/{id}")
    @AopLogAnnotation("根据id 查找分类")
    public R findCategoryById(@PathVariable Long id) {
        Categories category = categoriesService.getCategoryById(id);
        return R.setOK(category);
    }



}
