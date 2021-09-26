package com.eipen.mybatis;

import com.eipen.mybatis.mapper.UserMapper;
import com.eipen.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestMybatis {

    private UserMapper userMapper;
    @Before
    public void before() throws IOException {
        //获取配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        //以配置文件获取工厂对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //获取mapper对象
        userMapper = sqlSession.getMapper(UserMapper.class);

    }


    @Test
    public void findUserById() throws IOException {
        //获取配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        //获取 配置文件工厂对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //利用对象过去SQLSession
        SqlSession sqlSession = sessionFactory.openSession();
        //使用sqlSession获取mapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //调用方法，获取结果
        User user = userMapper.findUserById(2L);
        System.err.println(user);
    }

    @Test
    public void findUserByUsernameAndPassword() throws IOException {
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        //以配置文件创建工厂对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //使用工厂对象来获取 sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //获取mapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //获取方法 执行获取结果
        User user = userMapper.findUserByUsernameAndPassword("jack", "123456");
        System.err.println(user);
    }

    @Test
    public void findUserByUnameAndPwd() throws IOException {
        //获取配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        //用配置文件创建工厂对象
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //用工厂对象获取sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //使用session获取mapper 对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //获取方法执行结果
        User user = userMapper.findUserByUnameAndPwd("jack", "123456");
        System.err.println(user);
    }

    @Test
    public void findUserByUnameAndPwdMap(){
        Map map=new HashMap();
        map.put("uname","jack");
        map.put("pwd","123456");
        User user = userMapper.findUserByUnameAndPwdMap(map);
        System.err.println(user);
    }


}
