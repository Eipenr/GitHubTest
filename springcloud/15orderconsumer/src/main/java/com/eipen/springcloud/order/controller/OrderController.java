package com.eipen.springcloud.order.controller;

import com.eipen.springcloud.order.Feign.ItemFeignClient;
import com.eipen.springcloud.order.pojo.Order;
import com.eipen.springcloud.order.pojo.OrderItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private ItemFeignClient itemFeignClient;

    @Autowired
    public void setItemFeignClient(ItemFeignClient itemFeignClient) {
        this.itemFeignClient = itemFeignClient;
    }

    @GetMapping("/order/{id}")
    @HystrixCommand(fallbackMethod = "orderjiangji")
    public Order getOrderById(@PathVariable Integer id){
        //TODO 在执行这个方法的时候可能会出现一些异常,在访问量较大的时候可能会导致服务的瘫痪,所以我们要进行服务的降级和相关熔断的处理
        Order order=new Order();
        order.setId(id);
        order.setAddr("香山");
        OrderItem orderItem = itemFeignClient.getOrderById(id+1);
        order.setOrderItem(orderItem);
//        if(new Random().nextBoolean()){
//            int i=1/0;
//        }
        System.err.println("执行了原始方法-----------");
        return order;
    }

    @GetMapping("/test1")
    public String test1(){
        return itemFeignClient.test1("zhangsan",10);
    }
    @GetMapping("/test2")
    public OrderItem test2(){
        OrderItem orderItem=new OrderItem();
        orderItem.setId(11111);
        orderItem.setName("waocao");
        orderItem.setCategory("buzhidao");
        orderItem.setPrice(234234);
        return itemFeignClient.test2(orderItem);
    }



    public Order orderjiangji(Integer id){
        Order order=new Order();
        order.setId(id);
        order.setAddr("降级操作完成");
        order.setOrderItem(null);
        System.err.println("执行了降级方法");
        return order;
    }

}
