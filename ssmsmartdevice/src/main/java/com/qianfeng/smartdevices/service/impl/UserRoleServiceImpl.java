package com.qianfeng.smartdevices.service.impl;

import com.qianfeng.smartdevices.mapper.UserRoleMapper;
import com.qianfeng.smartdevices.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleMapper userRoleMapper;

    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<Long> findRoleByUserId(Long uid) {
        List<Long> roleByUserId = userRoleMapper.findRoleByUserId(uid);
        return roleByUserId;
    }

    @Override
    public void addRoleByUserId(Long uid, List<Long> rids) {
        rids.forEach(rid -> {
            userRoleMapper.addRoleByUserId(uid,rid);
        });
    }
}
