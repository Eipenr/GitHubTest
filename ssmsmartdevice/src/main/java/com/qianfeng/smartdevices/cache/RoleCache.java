package com.qianfeng.smartdevices.cache;

import com.qianfeng.smartdevices.event.UpdateRoleEvent;
import com.qianfeng.smartdevices.mapper.RoleMapper;
import com.qianfeng.smartdevices.pojo.Role;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RoleCache extends BaseCache<Long, Role, UpdateRoleEvent>{

    private RoleMapper roleMapper;


    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public void initData() {
        List<Role> allData = getAllData();
        List<Role> allRole = roleMapper.findAllRole();

        allData.clear();
        allData.addAll(allRole);
        Map<Long, Role> valueMap = getValueMap();
        valueMap.clear();

        allRole.forEach(role -> valueMap.put(role.getId(),role));

    }

    @Override
    public void onEvent(UpdateRoleEvent event) {
        super.onEvent(event);
    }
}
