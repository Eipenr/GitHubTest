package com.qianfeng.smartdevices.mapper;

import com.qianfeng.smartdevices.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleMapper {

    @Select("select * from role r where r.id in(select role_id from user_role ur where ur.user_id=#{uid} ) ")
    List<Role> findRoleByUserId(Long uid);

    void addRole(Role role);

    List<Role> findAllRole();

    void deleteRole(@Param("rid") List<Long> rid);

    void updateRole(Role role);


}
