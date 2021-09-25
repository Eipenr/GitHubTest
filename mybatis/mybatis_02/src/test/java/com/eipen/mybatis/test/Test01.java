package com.eipen.mybatis.test;

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

public class Test01 {
    private UserMapper userMapper;
    @Before
    public void before() throws IOException {
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        //以配置文件创建工厂
        SqlSessionFactory sessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession
        SqlSession sqlSession = sessionFactory.openSession();
        //获取代理对象
        userMapper = sqlSession.getMapper(UserMapper.class);
    }


    @Test
    public void findUserById(){
        // 执行方法 获取结果
        User user = userMapper.findUserById(1L);
        System.err.println(user);
    }

    @Test
    public void findUserByUnameAndPwd(){
        //执行方法
        User user = userMapper.findByUnameAndPwd("jack", "123456");
        System.err.println(user);
    }

    @Test
    public void findUserByUnameAndPwdMap(){
        Map map=new HashMap();
        map.put("username","jack");
        map.put("password","123456");
        User user = userMapper.findByUnameAndPwdMap(map);
        System.err.println(user);
    }

    @Test
    public void findByUsernameAndPassword(){
        User user = userMapper.findByUsernameAndPassword("jack", "123456");
        System.err.println(user);
    }

}
