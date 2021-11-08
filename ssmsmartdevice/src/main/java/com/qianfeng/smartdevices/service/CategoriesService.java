package com.qianfeng.smartdevices.service;

import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.pojo.Categories;

import java.util.List;

public interface CategoriesService {

    void addCategories(Categories categories);

    PageInfo<Categories> findAllCategories(int page, int limit, String categoryname,Long status);

    void deleteCategory(List<Integer> ids);

    void updateCategory(Categories categories);

    Categories getCategoryById(Long id);
}
