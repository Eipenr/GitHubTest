package com.qianfeng.smartdevices.service.impl;


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


import com.alibaba.druid.sql.builder.UpdateBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.dto.ResultCode;
import com.qianfeng.smartdevices.exceptions.AddErrorException;
import com.qianfeng.smartdevices.exceptions.DeleteErrorException;
import com.qianfeng.smartdevices.exceptions.UpdateErrorException;
import com.qianfeng.smartdevices.mapper.UserMapper;
import com.qianfeng.smartdevices.pojo.CheckStatus;
import com.qianfeng.smartdevices.pojo.User;
import com.qianfeng.smartdevices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jackiechan on 2021/10/11 15:03
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(User user) throws Exception{
        //先对数据进行判断


        boolean isNull = user.isNull(CheckStatus.ADD);
        if (isNull) {
            //数据不符合我们的要求,还有必要继续执行吗?返回结果给前端
            throw new AddErrorException("数据不完整,请检查数据", ResultCode.DATA_NOT_ALLOW_NUL);
        }
        //然后对密码进行散列
        //将数据保存到数据库中
        userMapper.addUser(user);

    }

    @Override
    public PageInfo<User> findAllUser(int page,int limit,String username, String  status) {
        PageHelper.startPage(page,limit);
        List<User> allUser = userMapper.findAllUser(username,status);
        allUser.forEach(user -> user.setPassword(null));//将密码设置为null、这样jackson在序列化的时候就不会将password 写出去
        PageInfo<User> pageInfo = new PageInfo<>(allUser);
        return pageInfo;
    }

    @Override
    public void deleteUserByIds(Long[] ids) {
        if(ids==null||ids.length==0){
            throw  new DeleteErrorException("请选择要删除的id",ResultCode.DATA_NOT_ALLOW_NUL);
        }
        userMapper.deleteUserByIds(Arrays.asList(ids));
    }

    @Override
    public void updateUser(User user) {
        if (user.isNull(CheckStatus.UPDATE)){
            throw new UpdateErrorException("更新的数据为空",ResultCode.DATA_NOT_ALLOW_NUL);
        }
        userMapper.updateUser(user);
    }

    @Override
    public User findUserByUserName(String userName) {
       return userMapper.findUserByName(userName);
    }


}
