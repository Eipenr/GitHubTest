package com.eipen.mybatis.mapper;

import com.eipen.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {
    User findUserById(long uid);

    User findUserByUsernameAndPassword(String username,String password);

    User findUserByUnameAndPwd(@Param("uname") String username, @Param("pwd") String password);

    User findUserByUnameAndPwdMap(Map map);
}
