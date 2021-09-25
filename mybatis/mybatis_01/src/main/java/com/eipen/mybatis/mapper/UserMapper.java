package com.eipen.mybatis.mapper;

import com.eipen.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {

    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User findByUsernameAndPasswordMap(Map map);


}
