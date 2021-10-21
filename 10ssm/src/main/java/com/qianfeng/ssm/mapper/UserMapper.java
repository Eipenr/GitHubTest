package com.qianfeng.ssm.mapper;


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


import com.qianfeng.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by jackiechan on 2021/9/24 11:12
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */

public interface UserMapper {


    User findUserById(long id);

    User findUserBuNameAndPassword(@Param("username") String username, @Param("password") String password);

    User findUserByEmailAndPhone(@Param("email") String email, @Param("phonenum") String phonenum);

    User findUserByEmailAndPhoneWhere(@Param("email") String email, @Param("phonenum") String phonenum);

    int updateUserById(User user);

    int updateUserByIdwithSet(User user);

    int updateUserState(@Param("username") String username, @Param("state") Integer state,@Param("targetState")  Integer targetState);


    List<User> findUsersByIdIn(@Param("ids") List<Long> ids);

    @Select("SELECT * FROM user")
    List<User> findAllUser();
}
