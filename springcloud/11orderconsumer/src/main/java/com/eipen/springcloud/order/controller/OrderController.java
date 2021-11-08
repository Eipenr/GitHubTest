package com.eipen.springcloud.order.controller;

import com.eipen.springcloud.order.Feign.ItemFeignClient;
import com.eipen.springcloud.order.pojo.Order;
import com.eipen.springcloud.order.pojo.OrderItem;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private ItemFeignClient itemFeignClient;

    @Autowired
    public void setItemFeignClient(ItemFeignClient itemFeignClient) {
        this.itemFeignClient = itemFeignClient;
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable Integer id){
        Order order=new Order();
        order.setId(id);
        order.setAddr("香山");
        OrderItem orderItem = itemFeignClient.getOrderById(id+1);
        order.setOrderItem(orderItem);
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

}
