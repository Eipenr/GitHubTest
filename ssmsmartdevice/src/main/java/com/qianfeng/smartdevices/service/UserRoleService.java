package com.qianfeng.smartdevices.service;

import java.util.List;

public interface UserRoleService {

    List<Long> findRoleByUserId(Long uid);

    void addRoleByUserId(Long uid,List<Long> rid);

}
