package com.qianfeng.smartdevices.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.cache.CategoriesCache;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.dto.ResultCode;
import com.qianfeng.smartdevices.event.CategoryChangeEvent;
import com.qianfeng.smartdevices.event.UpdateAreasCacheEvent;
import com.qianfeng.smartdevices.exceptions.AddErrorException;
import com.qianfeng.smartdevices.exceptions.DeleteErrorException;
import com.qianfeng.smartdevices.exceptions.MybaseException;
import com.qianfeng.smartdevices.exceptions.UpdateErrorException;
import com.qianfeng.smartdevices.mapper.CategoriesMapper;
import com.qianfeng.smartdevices.pojo.Areas;
import com.qianfeng.smartdevices.pojo.Categories;
import com.qianfeng.smartdevices.pojo.CheckStatus;
import com.qianfeng.smartdevices.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {

    private CategoriesMapper categoriesMapper;
    private CategoriesCache categoriesCache;
    private ApplicationContext context;

    @Autowired
    public void setCategoriesCache(CategoriesCache categoriesCache) {
        this.categoriesCache = categoriesCache;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setCategories(CategoriesMapper categoriesMapper) {
        this.categoriesMapper = categoriesMapper;
    }

    @Override
    public void addCategories(Categories categories) {
        //添加分类前要进行判断

        boolean isNull = categories.isNull(CheckStatus.ADD);
        if (isNull) {
            //数据不符合我们的要求,还有必要继续执行吗?返回结果给前端
            throw new AddErrorException("数据不完整,请检查数据", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        categoriesMapper.addCategories(categories);
        context.publishEvent(new CategoryChangeEvent());

    }

    @Override
    public PageInfo<Categories> findAllCategories(int page,int limit, String categoryname,Long status ) {
//        PageHelper.startPage(page,limit);
//        List<Categories> allCategories = categoriesMapper.findAllCategories(categoryname,status);
//        PageInfo<Categories> pageInfo = new PageInfo<>(allCategories);
//        return pageInfo;
        PageHelper.startPage(page, limit);
//        List<Areas> areasList = areaMapper.findAllAreasByNameLikeAndStatusEquals(areaname, status, parentid);
        List<Categories> areasList = categoriesCache.getAllData();//从缓存中获取数据
//        System.err.println(areasList);
        List<Categories> subList = areasList.stream().filter(areas -> {
            boolean b1 = ObjectUtils.isEmpty(categoryname) ? true : areas.getCategoryname().contains(categoryname);
            boolean b2 = (ObjectUtils.isEmpty(status)||status==-100)?true:status==areas.getStatus();
            return b1&&b2;
        }).collect(Collectors.toList());
        List<Categories> collect = subList.stream().skip((page - 1) * limit).limit(limit).collect(Collectors.toList());
        PageInfo<Categories> pageInfo = new PageInfo<>(collect);
        pageInfo.setTotal(subList.size());
        return pageInfo;
    }

    @Override
    public void deleteCategory(List<Integer> ids) {
        if(ids==null||ids.size()==0){
            throw new DeleteErrorException("请选择你要删除的数据",ResultCode.DATA_NOT_ALLOW_NUL);
        }
        categoriesMapper.deleteCategory(ids);
        context.publishEvent(new CategoryChangeEvent());
    }

    @Override
    public void updateCategory(Categories categories) {
        if(categories.isNull(CheckStatus.UPDATE)){
            throw new UpdateErrorException("更新数据为空",ResultCode.DATA_NOT_ALLOW_NUL);
        }
        categoriesMapper.updateCategory(categories);
        context.publishEvent(new CategoryChangeEvent());
    }

    @Override
    public Categories getCategoryById(Long id) {
        if (id == null) {
            throw new MybaseException("id 不能为空", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        Categories category = categoriesCache.get(id);
        if (category != null) {
            return category;
        }
        throw new MybaseException("无此分类", ResultCode.DATA_NOT_ALLOW_NUL);
    }


}
