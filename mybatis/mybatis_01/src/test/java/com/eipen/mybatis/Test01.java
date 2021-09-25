package com.eipen.mybatis;


import com.eipen.mybatis.mapper.UserMapper;
import com.eipen.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class Test01 {
    private UserMapper userMapper;

    @Before
    public void before() throws IOException {
        //加载配置文件
        String resource="buzhidao.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //使用上面的配置文件构建sql 会话的工厂
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //利用工厂来创建SQL 的会话 和 mysql 服务进行建立连接
        SqlSession sqlSession = sessionFactory.openSession();
        //通过会话来构建一个mapping的代理对象，在内部会通过反射来创建对象来创建对象并拦截操作
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void selectByUsernameAndPassword() throws IOException {
        String resource="buzhidao.xml";
        // 将mybatis 的配置文件加载进来
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //用 上面的配置文件来进行构建 sql 会话的工厂
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂来创建SQL 的会话 和 MySQL服务器建立连接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过会话来构建一个 mapping 的代理对象，在内部会通过反射来创建对象来创建对象并拦截操作
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法 执行SQL 获取结果
        User user = userMapper.findUserByUsernameAndPassword("hikitty", "123456");
        System.err.println(user);
    }

    @Test
    public void findByUsernameAndPasswordMap(){
        Map map=new HashMap();
        map.put("uname","jack");
        map.put("pwd","123456");
        User user = userMapper.findByUsernameAndPasswordMap(map);
        System.err.println(user);
    }
}
