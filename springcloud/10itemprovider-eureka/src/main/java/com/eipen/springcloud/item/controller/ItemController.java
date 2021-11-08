package com.eipen.springcloud.item.controller;

import com.eipen.springcloud.item.pojo.Item;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private int count;
    @GetMapping("/item/{id}")
    public Item getItemById(@PathVariable Integer id){

        count++;
        System.err.println("收到了请求"+count);
        Item item=new Item();
        item.setId(id);
        item.setCategory("硅胶产品");
        item.setName("打气筒");
        item.setPrice(10000);
        return item;

    }

    @GetMapping("/test1")
    public String test1(String username,Integer age){

        return "xingmingshi"+username+",nianlingshi"+age;
    }
    @PostMapping("/test2")
    public Item test2(@RequestBody Item item){
        item.setName(item.getName()+"sdafsdafsdfasdf");
        return item;
    }






}
