package com.qianfeng.smartdevices.mapper;

import java.util.List;

public interface UserRoleMapper {

    List<Long> findRoleByUserId(Long uid);

    void addRoleByUserId(Long uid,Long rid);

}
