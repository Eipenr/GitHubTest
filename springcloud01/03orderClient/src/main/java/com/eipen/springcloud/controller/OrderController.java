package com.eipen.springcloud.controller;

import com.eipen.springcloud.feign.ItemFeignClient;
import com.eipen.springcloud.pojo.Order;
import com.eipen.springcloud.pojo.OrderItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @HystrixCommand(fallbackMethod = "jiangjifangfa")
    public Order getOrderById(@PathVariable int id){
        Order order=new Order();
        order.setId(id);
        order.setMsg("发生了不可描述的事情");
        order.setStatus("正在进行中.....");
        OrderItem itemById = itemFeignClient.getItemById(id);
        order.setOrderItem(itemById);
        if (new Random().nextBoolean()){
            int i=1/0;
        }
        return order;
    }

    public Order jiangjifangfa(int id){
        Order order=new Order();
        order.setId(id);
        order.setMsg("-------------");
        order.setStatus("+++++++++++");
        OrderItem itemById = itemFeignClient.getItemById(id);
        order.setOrderItem(itemById);
        return order;
    }



}
