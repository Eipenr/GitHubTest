package com.eipen.springcloud.controller;

import com.eipen.springcloud.pojo.Item;
import org.aspectj.weaver.ast.Var;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    private int count;
    @GetMapping("/item/{id}")
    public Item getItemById(@PathVariable int id){
        System.err.println(count++);
        Item item=new Item();
        item.setId(id);
        item.setName("七个小矮人");
        item.setMiaoshu("和一个白雪公主");
        item.setPrice(1000000);
        return item;
    }





}
