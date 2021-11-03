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

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable  Integer id) {

        Order order = new Order();
        order.setId(id);
        order.setAddress("沙阳路 38 号北科");
        //现在需要订单的商品数据, 但是当前程序没有查询商品的功能,有一个单独的程序具有查询商品的功能
        //通过代码请求我们的商品的服务,获取商品的数据
        ///经过测试可以实现访问获取数据,但是遇到了一个问题,如果这个地址对应的服务器出现了问题,就有会导致请求失败,即便我们有多个服务器提供功能
        //但是因为地址写死了,所以只要这个地址对应的服务器没开,其他服务器开着也没用
        //最好的办法是动态获取服务器地址,因为我们的地址变化可能比较快
        //可以考虑放到数据库,每次先从数据库查询地址,然后请求,但是如果机器坏了 可能无法及时更新到数据库,需要想办法更新
        OrderItem orderItem = restTemplate.getForObject("http://localhost:11000/items/item/" + id, OrderItem.class);
        order.setOrderItem(orderItem);
        return order;
    }
}
