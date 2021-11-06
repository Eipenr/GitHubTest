package com.eipen.springcloud.feign;

import com.eipen.springcloud.pojo.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("02ITEMCLIENT")
@RequestMapping("/items")
public interface ItemFeignClient {

    @GetMapping("/item/{id}")
    OrderItem getItemById(@PathVariable int id);

}
