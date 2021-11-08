package com.eipen.springcloud.item.controller;

import com.eipen.springcloud.item.pojo.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {


    @GetMapping("/item/{id}")
    public Item getItemById(@PathVariable Integer id){

        Item item=new Item();
        item.setId(id);
        item.setCategory("硅胶产品");
        item.setName("打气筒");
        item.setPrice(10000);
        return item;

    }


}
