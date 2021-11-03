package com.qianfeng.springcloud.order;


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


import com.qianfeng.springcloud.order.annos.A;
import com.qianfeng.springcloud.order.config.MyRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jackiechan on 2021/11/3 11:24
 *
 * @author jackiechan
 * @version 1.0
 * //TODO 给最美丽的芳姐介绍学生
 * @since 1.0
 */
@SpringBootApplication
//给名字叫04ITEMPROVIDER-EUREKA的服务设置了自定义的配置
@RibbonClient(name = "04ITEMPROVIDER-EUREKA",configuration = MyRibbonConfig.class )
@ComponentScan(excludeFilters ={@ComponentScan.Filter(A.class)} )//忽略包含特定注解的类
public class OrderConsumerApplication {
    public static void main (String[] args){
        SpringApplication.run(OrderConsumerApplication.class,args);
    }

    @Bean
    @LoadBalanced//开启负载均衡,会自动先去注册中检查我们访问的地址是不是它里面的服务,如果是会自动获取到一个地址替换过来
    public RestTemplate template() {
        return new RestTemplate();
    }
}
