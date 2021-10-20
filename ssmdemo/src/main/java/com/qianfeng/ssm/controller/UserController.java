package com.qianfeng.ssm.controller;


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
import com.qianfeng.ssm.dto.R;
import com.qianfeng.ssm.pojo.User;
import com.qianfeng.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jackiechan on 2021/10/8 10:51
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

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/user")
    public R addUser(@RequestBody User user) {
        userService.addUser(user);
        return R.setOk();
    }

    @GetMapping("/user/{id}")
    public R findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return R.setOk(user);
    }
    @GetMapping("/users")
    public R findAllUserByPage(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "2") int pageSize) {
        PageInfo<User> pageInfo = userService.findUserByPage(pageNum, pageSize);
        return R.setOk(pageInfo);
    }
}
