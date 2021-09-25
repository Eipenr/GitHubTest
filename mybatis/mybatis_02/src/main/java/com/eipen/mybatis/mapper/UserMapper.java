package com.eipen.mybatis.mapper;

import com.eipen.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {
    User findUserById(long id);

    User findByUnameAndPwd(@Param("uname") String username, @Param("pwd") String password);

    User findByUnameAndPwdMap(Map map);

    User findByUsernameAndPassword(String username,String password);
}
