package com.qianfeng.springboot.controller;

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
//                  佛祖镇楼                  BUG辟易  
//          佛曰:  
//                  写字楼里写字间，写字间里程序员；  
//                  程序人员写程序，又拿程序换酒钱。  
//                  酒醒只在网上坐，酒醉还来网下眠；  
//                  酒醉酒醒日复日，网上网下年复年。  
//                  但愿老死电脑间，不愿鞠躬老板前；  
//                  奔驰宝马贵者趣，公交自行程序员。  
//                  别人笑我忒疯癫，我笑自己命太贱；  
//  


import com.qianfeng.springboot.pojo.ZijiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jackiechan on 2021/10/25/10:18
 *
 * @author Jackiechan
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private String name;

    private String password;


    private ZijiProperties zijiProperties;


    @Autowired
    public void setZijiProperties(ZijiProperties zijiProperties) {
        this.zijiProperties = zijiProperties;
    }

    @Value("${yonghu.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${yonghu.name}")
    public void setName(String name) {
        this.name = name;
    }

    @GetMapping("/name")
    public String getName() {
        return name+"---->"+password;
    }

    @GetMapping("/ziji")
    public ZijiProperties getZijiProperties() {
        return zijiProperties;
    }
}
