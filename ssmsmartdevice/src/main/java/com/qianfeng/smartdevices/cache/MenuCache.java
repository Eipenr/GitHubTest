package com.qianfeng.smartdevices.cache;

import com.qianfeng.smartdevices.event.UpdateMenuEvent;
import com.qianfeng.smartdevices.mapper.MenusMapper;
import com.qianfeng.smartdevices.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MenuCache extends BaseCache<Long, Menu, UpdateMenuEvent>{


    private MenusMapper menusMapper;

    @Autowired
    public void setMenusMapper(MenusMapper menusMapper) {
        this.menusMapper = menusMapper;
    }

    @Override
    public void initData() {

        List<Menu> allData = getAllData();
        List<Menu> allMenus = menusMapper.findAllMenus();
        allData.clear();
        allData.addAll(allMenus);
        Map<Long, Menu> valueMap = getValueMap();
        valueMap.clear();
        allMenus.forEach(menu -> valueMap.put(menu.getId(),menu));

    }

    @Override
    public void onEvent(UpdateMenuEvent event) {
        super.onEvent(event);
    }
}
