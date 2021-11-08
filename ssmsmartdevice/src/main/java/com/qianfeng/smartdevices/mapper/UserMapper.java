package com.qianfeng.smartdevices.mapper;
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


import com.qianfeng.smartdevices.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jackiechan on 2021/10/11 11:45
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */

public interface UserMapper {

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);


    User findUserById(Long id);

    /**
     * 按照用户名和密码查询,一般不用,因为密码是散列值,原始内容查询一定查询不到
     * @param username
     * @param password
     * @return
     */
    User findUserByNameAndPwd(String username, String password);

    /**
     * 按照用户名查询用户信息,更多的使用方式
     * @param username
     * @return
     */
    User findUserByName(String username);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户,实际上是将用户的状态更新掉
     * @param id
     * @return
     */
    int deleteUser(Long id);

    /**
     * 按照用户名或者状态查询, 用户名和状态都可以不传递
     * @param username
     * @param status
     * @return
     */
    List<User> findUsersByNameLikeWithStatusEquals(String username, Integer status);

    List<User> findAllUser(@Param("username") String username,@Param("status") String status);

    void deleteUserByIds(@Param("ids") List<Long> ids);




}
