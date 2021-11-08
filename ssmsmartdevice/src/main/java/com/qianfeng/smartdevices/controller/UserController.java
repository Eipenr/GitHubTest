package com.qianfeng.smartdevices.controller;


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


import com.github.pagehelper.PageInfo;
import com.qianfeng.smartdevices.annotation.AopLogAnnotation;
import com.qianfeng.smartdevices.dto.R;
import com.qianfeng.smartdevices.pojo.User;
import com.qianfeng.smartdevices.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jackiechan on 2021/10/11 15:25
 *
 * @author jackiechan
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    @AopLogAnnotation("添加用户")
    public R addUser(@RequestBody User user) throws Exception {
        userService.addUser(user);
        return R.setOK();
    }

    @GetMapping("/users")
    @AopLogAnnotation("查找用户")
    public R findAllUser(@RequestParam int page, @RequestParam int limit,String username,@Param("status") String status){
        PageInfo<User> allUser = userService.findAllUser(page, limit,username,status);
        return R.setOK(allUser);
    }

    @DeleteMapping("/users")
    @AopLogAnnotation("删除用户")
    public R deleteUserByIds(Long[] ids){
        userService.deleteUserByIds(ids);
        return R.setOK();
    }

    @PutMapping("/user")
    @AopLogAnnotation("更新用户")
    public R updateUser(@RequestBody User user){
        userService.updateUser(user);
        return R.setOK();
    }




}
