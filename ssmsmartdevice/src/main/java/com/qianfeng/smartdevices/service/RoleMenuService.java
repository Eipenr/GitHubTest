package com.qianfeng.smartdevices.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuService {

    List<Long> findMenuByRole(@Param("rid") Long rid);

    void addMenuToRole(Long role,List<Long> mids);

    void deleteRoleMenus(Long role,List<Long> mids);

//    void deleteAllMenusByRoleId(Long rid);

    void updateRoleMenus(Long rid,List<Long> mid);

}
