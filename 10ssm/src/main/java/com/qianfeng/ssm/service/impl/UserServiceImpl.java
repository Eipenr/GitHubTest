package com.qianfeng.ssm.service.impl;


//
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//  
//         .............................................  
//                  佛祖镇楼            BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.ssm.mapper.UserMapper;
import com.qianfeng.ssm.pojo.User;
import com.qianfeng.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jackiechan on 2021/9/27 17:21
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional// 对当前类中的所有的方法开启事务管理,全部采取默认设置
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public PageInfo<User> findAllUser() {
        PageHelper.startPage(2,2);
        List<User> allUser = userMapper.findAllUser();
        return new PageInfo<>(allUser);
    }

    @Override
    @Transactional(noRollbackFor ={ArithmeticException.class} )//如果某个方法需要单独配置,就在这个方法上面单独加注解进行配置即可
    public void updateUser(User user) {
        //开启事务
        userMapper.updateUserByIdwithSet(user);
        //提交事务
        //
        ///
        //
        //
        ///
        //
        ///
        //
        //
        //
        int i = 1 / 0;
    }


}
