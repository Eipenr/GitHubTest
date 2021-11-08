package com.eipen.springcloud.order.Feign;


import com.eipen.springcloud.order.pojo.Order;
import com.eipen.springcloud.order.pojo.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("10ITEMPROVIDER-EUREKA")
@RequestMapping("/items")
public interface ItemFeignClient {

    @GetMapping("/item/{id}")
     OrderItem getOrderById(@PathVariable Integer id);

    @GetMapping("/test1")
     String test1(@RequestParam String username,@RequestParam Integer age);

    @PostMapping("/test2")
    OrderItem test2(@RequestBody OrderItem item);



}
