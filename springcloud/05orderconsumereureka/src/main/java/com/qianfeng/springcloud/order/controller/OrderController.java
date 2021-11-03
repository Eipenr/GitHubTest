package com.qianfeng.springcloud.order.controller;


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


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.qianfeng.springcloud.order.pojo.Order;
import com.qianfeng.springcloud.order.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jackiechan on 2021/11/3 11:17
 *
 * @author jackiechan
 * @version 1.0
 * //TODO 给最美丽的芳姐介绍学生
 * @since 1.0
 */
@RestController
@RequestMapping("/orders")
public class OrderController {


    private RestTemplate restTemplate;

    //eureka 客户端
    private EurekaClient eurekaClient;

    @Autowired
    public void setEurekaClient(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable  Integer id) {

        Order order = new Order();
        order.setId(id);
        order.setAddress("沙阳路 38 号北科");
        //通过 eureka 获取地址,参数 1 程序在 eureka 中的名字
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("04ITEMPROVIDER-EUREKA", false);
        String url = instanceInfo.getHomePageUrl();//获取到了服务器地址
        System.err.println(url);
        OrderItem orderItem = restTemplate.getForObject(url+"items/item/" + id, OrderItem.class);
        order.setOrderItem(orderItem);
        return order;
    }
}
