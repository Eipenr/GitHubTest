package com.eipen.springcloud.item.controller;

import com.eipen.springcloud.item.pojo.Item;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/items")
public class ItemController {

    private int count;
    @GetMapping("/item/{id}")
    @HystrixCommand(fallbackMethod = "jiangjifangfa")
    public Item getItemById(@PathVariable Integer id){

        //TODO 在执行这个方法的时候可能会出现一些异常,在访问量较大的时候可能会导致服务的瘫痪,所以我们要进行服务的降级和相关熔断的处理
        count++;
        System.err.println("收到了请求"+count);
        Item item=new Item();
        item.setId(id);
        item.setCategory("硅胶产品");
        item.setName("打气筒");
        item.setPrice(10000);
//        if(new Random().nextBoolean()){
//            int i=1/0;
//        }
        return item;

    }

    public Item jiangjifangfa(Integer id){
//        count++;
        System.err.println("降级方法执行了");
        Item item=new Item();
        item.setId(id);
        item.setCategory("降级方法执行了");
        item.setName("降级");
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
