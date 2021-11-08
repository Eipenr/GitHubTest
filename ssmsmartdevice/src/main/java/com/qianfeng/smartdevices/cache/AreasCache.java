package com.qianfeng.smartdevices.cache;

import com.qianfeng.smartdevices.event.UpdateAreasCacheEvent;
import com.qianfeng.smartdevices.mapper.AreaMapper;
import com.qianfeng.smartdevices.pojo.Areas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.objenesis.instantiator.perc.PercInstantiator;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.*;

/**
 * 缓存类
 */

@Component
public class AreasCache extends BaseCache<Long,Areas,UpdateAreasCacheEvent>{

    @Autowired
    private AreaMapper mapper;

    @Override
    public void initData() {
        //初始化数据
        List<Areas> allData = getAllData();
        //查询数据库
        List<Areas> allAreas =mapper.findAllAreasByNameLikeAndStatusEquals(null,null,null);
        //保存到set集合
        allData.clear();
        allData.addAll(allAreas);
        Map<Long,Areas> valueMap=getValueMap();//获取单个数据的映射
        //遍历 set 集合
        //一个一个放到map中
        valueMap.clear();
        allAreas.forEach(area -> valueMap.put(area.getId(),area));
    }

    /**
     * 只是简单覆盖的原因是为了防止泛型擦除
     * @param event
     */
    @Override
    public void onEvent(UpdateAreasCacheEvent event) {
        super.onEvent(event);
    }

    //    //用来存放所有区域的集合
//    private List<Areas> allAreas=new ArrayList<>();
//
//
//    private boolean isStarted;
//
//    private AreaMapper areaMapper;
//
//    private Map<Long,Areas> Key2AreaMap =new HashMap<>();//方便根据主键进行查询
//
//
//    public List<Areas> getAllAreas() {
//        return allAreas;
//    }
//
//
//    public Map<Long, Areas> getKey2AreaMap() {
//        return Key2AreaMap;
//    }
//
//    @Autowired
//    public void setAreaMapper(AreaMapper areaMapper) {
//        this.areaMapper = areaMapper;
//    }
//
//    public void init(){
//        List<Areas> areasList = areaMapper.findAllAreasByNameLikeAndStatusEquals(null, null, null);
//        allAreas.clear();
//        allAreas.addAll(areasList);
//        allAreas.forEach( areas -> Key2AreaMap.put(areas.getId(),areas));//保存在map中
//        System.err.println(allAreas);
//    }
//
//
//    @EventListener
//    public void onEvent(ContextRefreshedEvent event){
//        if(!isStarted){
//            isStarted=true;
//            System.err.println("程序启动了");
//            init();
//        }
//    }
//
//    @EventListener
//    public void onEvent(UpdateAreasCacheEvent event){
//       super.onEvent(event);
//    }


}
