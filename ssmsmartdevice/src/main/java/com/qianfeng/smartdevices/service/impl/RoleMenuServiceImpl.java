package com.qianfeng.smartdevices.service.impl;

import com.qianfeng.smartdevices.mapper.RoleMenuMapper;
import com.qianfeng.smartdevices.service.RoleMenuService;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService {

    private RoleMenuMapper roleMenuMapper;


    @Autowired
    public void setRoleMenuMapper(RoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    @Override
    public List<Long> findMenuByRole(Long rid) {
       return roleMenuMapper.findMenuByRole(rid);
    }

    @Override
    public void addMenuToRole(Long role, List<Long> mids) {
        mids.stream().forEach(mid -> {
            roleMenuMapper.addMenuToRole(role,mid);
        });
    }

    @Override
    public void deleteRoleMenus(Long role, List<Long> mids) {
        mids.stream().forEach(mid -> {
            roleMenuMapper.deleteRoleMenu(role,mid);
        });
    }

    @Override
    public void updateRoleMenus(Long rid, List<Long> mids) {

        //先根据角色id 删除所有的权限，
        roleMenuMapper.deleteAllMenusByRoleId(rid);
        mids.stream().forEach(mid->{
            //再根据前端传过来的id进行添加
            roleMenuMapper.addMenuToRole(rid,mid);
        });


    }

//    @Override
//    public void deleteAllMenusByRoleId(Long rid) {
//        roleMenuMapper.deleteAllMenusByRoleId(rid);
//    }
}
