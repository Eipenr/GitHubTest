package com.qianfeng.smartdevices.cache;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存类的父类
 * @param <K> 单个数据保存在 map 中的 Key 的类型，一般就是主键类型
 * @param <V> 保存的数据的类型
 * @param <E> 监听的更新数据的事件类型，但是注意泛型在运行期间将会被擦除
 */
public abstract class BaseCache<K,V,E> {
    private List<V> allData =new ArrayList<>();
    private Map<K,V> valueMap=new HashMap<>();

    private boolean isStarted;

    /**
     * 没有声明为抽象，但是类声明为抽象，代表方法中必须有重写的方法
     */
    public void initData(){

    }

    public Map<K, V> getValueMap() {
        return valueMap;
    }

    public List<V> getAllData() {
        return allData;
    }

    public V get(K key){
        if (key==null){
            return null;
        }
        return valueMap.get(key);
    }

    @EventListener
    public void onEvent(E event){
        initData();
    }

    @EventListener
    public void onEvent(ContextRefreshedEvent event){
        if (!isStarted){
            isStarted=true;
            initData();
        }
    }



}
