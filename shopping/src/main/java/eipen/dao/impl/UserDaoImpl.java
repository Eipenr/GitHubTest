package eipen.dao.impl;

import eipen.Utils.DbUtils;
import eipen.Utils.SqlUtils;
import eipen.dao.UserDao;
import eipen.pojo.TbUser;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner=new QueryRunner(DbUtils.getDataSource());
    @Override
    public TbUser findUserByName(String username) throws SQLException {
        return queryRunner.query(SqlUtils.getSql("user.find.by.name"),new BeanHandler<>(TbUser.class),username);
    }

    @Override
    public void addUser(TbUser user) throws Exception {
        queryRunner.insert(SqlUtils.getSql("user.add"),new ScalarHandler<>(),user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),user.getGender(),user.getCode());
        System.err.println("注册成功");
    }

    @Override
    public int active(String username, String code) throws SQLException {
        return queryRunner.update(SqlUtils.getSql("user.active"), username, code);
    }
}
