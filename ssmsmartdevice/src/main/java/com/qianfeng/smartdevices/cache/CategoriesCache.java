package com.qianfeng.smartdevices.cache;

import com.qianfeng.smartdevices.event.CategoryChangeEvent;
import com.qianfeng.smartdevices.mapper.CategoriesMapper;
import com.qianfeng.smartdevices.pojo.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CategoriesCache extends BaseCache<Long, Categories , CategoryChangeEvent>{

    @Autowired
    private CategoriesMapper mapper;



    @Override
    public void initData() {

        List<Categories> allData = getAllData();//从父类中获取保存数据的集合
        List<Categories> allCategories = mapper.findAllCategories(null,null);
        allData.clear();
        allData.addAll(allCategories);
        Map<Long, Categories> valueMap = getValueMap();
        valueMap.clear();
        allCategories.forEach(categories -> valueMap.put(categories.getId(),categories));


    }

    @Override
    public void onEvent(CategoryChangeEvent event) {
        super.onEvent(event);
    }
}
