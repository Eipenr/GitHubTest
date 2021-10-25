package com.qianfeng.springboot;

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


import com.qianfeng.springboot.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Jackiechan on 2021/10/25/10:08
 *
 * @author Jackiechan
 * @version 1.0
 * @since 1.0
 */
//SpringBootApplication 是一个组合注解, 内置了包扫描的注解,可以代替我们之前配置文件中的<context:component-scan
    //默认包扫描的位置就是当前注解所在的类所在的包以及子包
@SpringBootApplication
//@ComponentScan(basePackages = {"com.qianfeng.springboot.config"})
public class StartApp {

    public static void main(String[] args) {
        //参数1, 哪个类上面有@SpringBootApplication注解就写谁
        //参数2  当前主方法的参数
        SpringApplication.run(StartApp.class, args);
    }
//
//    @Bean
//    public User user3() {
//        return new User();
//    }
}
