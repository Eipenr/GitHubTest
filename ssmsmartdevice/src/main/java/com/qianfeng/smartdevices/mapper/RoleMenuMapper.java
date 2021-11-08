package com.qianfeng.smartdevices.mapper;

import java.util.List;

public interface RoleMenuMapper {

    List<Long> findMenuByRole(Long rid);

    void addMenuToRole(Long role,Long mid);

    void deleteRoleMenu(Long role,Long mid);

    void deleteAllMenusByRoleId(Long rid);

    void updateRoleMenu(Long rid, Long mid);

}
