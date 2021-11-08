package com.qianfeng.smartdevices.service;

import com.qianfeng.smartdevices.pojo.Role;

import java.util.List;

public interface RoleService {

    //根据用户id 查询 角色
    List<Role> findRoleByUserId(Long uid);

    void addRole(Role role);

    List<Role> findAllRole();

    void deleteRole(List<Long> rid);

    void updateRole(Role role);
}
