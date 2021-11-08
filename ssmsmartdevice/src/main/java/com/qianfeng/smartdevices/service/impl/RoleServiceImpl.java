package com.qianfeng.smartdevices.service.impl;

import com.qianfeng.smartdevices.dto.ResultCode;
import com.qianfeng.smartdevices.exceptions.AddErrorException;
import com.qianfeng.smartdevices.exceptions.DeleteErrorException;
import com.qianfeng.smartdevices.exceptions.UpdateErrorException;
import com.qianfeng.smartdevices.mapper.RoleMapper;
import com.qianfeng.smartdevices.pojo.CheckStatus;
import com.qianfeng.smartdevices.pojo.Role;
import com.qianfeng.smartdevices.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RoleServiceImpl implements RoleService {


    private RoleMapper roleMapper;

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> findRoleByUserId(Long uid) {
        List<Role> roleList = roleMapper.findRoleByUserId(uid);
        return roleList;
    }

    @Override
    public void addRole(Role role) {
        if (role.isNull(CheckStatus.ADD)) {
            throw new AddErrorException("有必填项没有进行填写", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        roleMapper.addRole(role);//添加了一个角色，还要对角色进行权限的分配
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }

    @Override
    public void deleteRole(List<Long> rid) {
        if (rid==null){
            throw new DeleteErrorException("请选择要删除的id",ResultCode.DATA_NOT_ALLOW_NUL);
        }
        roleMapper.deleteRole(rid);
    }

    @Override
    public void updateRole(Role role) {
        if (role.isNull(CheckStatus.UPDATE)){
            throw new UpdateErrorException("你没有要更新的数据",ResultCode.DATA_NOT_ALLOW_NUL);
        }
        roleMapper.updateRole(role);
    }


}
