package eipen.service;

import eipen.pojo.TbUser;

import java.sql.SQLException;

public interface UserService {

    void addUser(TbUser user) throws Exception;

    int active(String username,String code) throws SQLException;

    TbUser login(String username,String password) throws SQLException;

}
