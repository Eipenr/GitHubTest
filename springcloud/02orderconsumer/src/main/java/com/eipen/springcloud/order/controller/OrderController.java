package com.eipen.springcloud.order.controller;

import com.eipen.springcloud.order.pojo.Order;
import com.eipen.springcloud.order.pojo.OrderItem;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/order/{id}")
    public Order getOrderById(@PathVariable Integer id){
        Order order=new Order();
        order.setId(id);
        order.setAddr("香山");
        OrderItem orderItem = restTemplate.getForObject("http://localhost:10001/items/item/" + id, OrderItem.class);
        order.setOrderItem(orderItem);
        return order;
    }
}
