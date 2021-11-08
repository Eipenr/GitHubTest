package com.qianfeng.smartdevices.mapper;

import com.qianfeng.smartdevices.pojo.Categories;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoriesMapper {

    //设备分类的添加
    void addCategories(Categories categories);

    //设备分类的查询
    List<Categories> findAllCategories(@Param("categoryname") String categoryname,@Param("status") Long status);

    //删除分类
    void deleteCategory(@Param("ids") List<Integer> ids);

    //更新分类
    void updateCategory(Categories categories);

}
