package com.qianfeng.smartdevices.service.impl;

import com.qianfeng.smartdevices.pojo.BaseUser;
import com.qianfeng.smartdevices.pojo.User;
import com.qianfeng.smartdevices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsManager {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户数据
        User user =userService.findUserByUserName(username);
        if (user==null){
            throw  new UsernameNotFoundException("用户名或者密码错误");
        }
        if(user.getStatus().equals("0")){
            throw new DisabledException("用户被禁用");
        }
        //将我们查询到的密码进行返回，spring 就会自动判断是不是，如果比较的是一致的，就会执行我们自定义的 successHandler
        BaseUser baseUser=new BaseUser(username,user.getPassword(),new ArrayList<>());
        baseUser.setUid(user.getId());
        return baseUser;

    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
