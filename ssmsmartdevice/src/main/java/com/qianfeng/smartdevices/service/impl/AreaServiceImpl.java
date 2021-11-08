package com.qianfeng.smartdevices.service.impl;


//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼            BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.cache.AreasCache;
import com.qianfeng.smartdevices.dto.ResultCode;
import com.qianfeng.smartdevices.event.UpdateAreasCacheEvent;
import com.qianfeng.smartdevices.exceptions.AddErrorException;
import com.qianfeng.smartdevices.exceptions.DeleteErrorException;
import com.qianfeng.smartdevices.exceptions.MybaseException;
import com.qianfeng.smartdevices.exceptions.UpdateErrorException;
import com.qianfeng.smartdevices.mapper.AreaMapper;
import com.qianfeng.smartdevices.pojo.Areas;
import com.qianfeng.smartdevices.pojo.CheckStatus;
import com.qianfeng.smartdevices.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jackiechan on 2021/10/12 14:49
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    private AreaMapper areaMapper;
    private AreasCache areasCache;
    private ApplicationContext context;

    @Autowired
    public void setAreaMapper(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    @Autowired
    public void setAreasCache(AreasCache areasCache) {
        this.areasCache = areasCache;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public List<Areas> findParentAreaByStatus(Long stauts) {
        return areaMapper.findParentAreaByStatus(stauts);
    }

    @Override
    public void addArea(Areas areas) throws Exception {
        if (areas.isNull(CheckStatus.ADD)) {
            throw new AddErrorException("添加的数据不能为空", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        areaMapper.addArea(areas);

        context.publishEvent(new UpdateAreasCacheEvent());

    }

    @Override
    public PageInfo<Areas> findAllAreasByNameLikeAndStatusEquals(int page, int limit, String areaname, Long status, Long parentid) {
        PageHelper.startPage(page, limit);
//        List<Areas> areasList = areaMapper.findAllAreasByNameLikeAndStatusEquals(areaname, status, parentid);
        List<Areas> areasList = areasCache.getAllData();//从缓存中获取数据
//        System.err.println(areasList);
        List<Areas> subList = areasList.stream().filter(areas -> {
            boolean b1 = ObjectUtils.isEmpty(areaname) ? true : areas.getAreaname().contains(areaname);
            boolean b2 = (ObjectUtils.isEmpty(status)||status==-100)?true:areas.getStatus().equals(status);
            boolean b3 = (ObjectUtils.isEmpty(parentid)||parentid==-100)?true:(ObjectUtils.isEmpty(areas.getParentid())?false:areas.getParentid().equals(parentid));
            return b1&&b2&b3;
        }).collect(Collectors.toList());
        List<Areas> collect = subList.stream().skip((page - 1) * limit).limit(limit).collect(Collectors.toList());
        PageInfo<Areas> pageInfo = new PageInfo<>(collect);
        pageInfo.setTotal(subList.size());
        return pageInfo;
    }

    @Override
    public void updateArea(Areas areas) {
        if (areas.isNull(CheckStatus.UPDATE)) {
            throw new UpdateErrorException("你没有更改数据，不需要更新", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        areaMapper.updateArea(areas);
        context.publishEvent(new UpdateAreasCacheEvent());
    }

    @Override
    public void deleteAreas(List<Long> ids) {
        if (ids == null || ids.size() == 0) {
            throw new DeleteErrorException("请选择你要删除的id", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        areaMapper.deleteAreas(ids);
        context.publishEvent(new UpdateAreasCacheEvent());
    }

    @Override
    public Areas getAreaById(Long id) {
        //我们本身就存放了所有数据的缓存,没有必要查询数据库,可以从缓存中查询
//        List<Areas> areasList = areasCache.getAllAreas();
//        //遍历缓存
//        List<Areas> list = areasList.stream().filter(areas -> areas.getId() == id).collect(Collectors.toList());
//        //我们查询数据库主键是有索引的,效率非常高
//
//        return list.size() == 0 ? null : list.get(0);
//        Map<Long, Areas> areasMap = areasCache.getKey2AreaMap();
        return areasCache.get(id);
    }

    @Override
    public List<Areas> getAreaByParentId(Long id) {
        if (id == null || id == 0) {
            throw new MybaseException("没有传递 id", ResultCode.DATA_NOT_ALLOW_NUL);
        }

        try {
            // List<Area> areaList = areaMapper.getAreaByParentId(id);
            List<Areas> allData = areasCache.getAllData();//从缓存查询所有数据
            List<Areas> areaList = allData.stream().filter(area -> id == area.getParentid()).collect(Collectors.toList());//获取父id和当前id一样的数据
            return areaList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MybaseException("查询失败", ResultCode.ERROR);

        }
    }

}
