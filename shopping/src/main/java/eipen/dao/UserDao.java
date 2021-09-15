package eipen.dao;

import eipen.pojo.TbUser;

import java.sql.SQLException;

public interface UserDao {
    TbUser findUserByName(String username) throws SQLException;

    void addUser(TbUser user) throws Exception;

    int active(String username,String code) throws SQLException;
}
