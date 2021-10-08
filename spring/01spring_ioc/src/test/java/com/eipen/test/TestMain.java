package com.eipen.test;

import com.eipen.dao.UserDao;
import com.eipen.dao.impl.UserDaoImpl;
import com.eipen.service.UserService;
import com.eipen.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    @Test
    public void TestBean(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.err.println(userService);
        userService.addUserService();
        UserDao userDao = (UserDao) context.getBean("userDao");
        System.err.println(userDao);
        userDao.addUser();
    }
}
