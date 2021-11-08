package com.eipen.springcloud.order;

import com.eipen.springcloud.anno.A;
import com.eipen.springcloud.order.config.MyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@RibbonClient(name = "12ITEMPROVIDER-EUREKA",configuration = MyConfiguration.class)//第二种方式 设置负载均衡的策略， 使用注解的方式， 但是需要创建一个config 类来声明一个对象（负载均衡规则的对象），然后需要注意的是这个类必须要在主运行程序的外层包下，也就是说不让自动扫描的程序扫这个类，否则会导致所有的服务都成了这个负载均衡的模式
@ComponentScan(excludeFilters = {@ComponentScan.Filter(A.class)})//不扫描 加了A 注解的类  这个注解加上之后，config类就没有必要非要放到主程序类之外的包下了，因为我们已经过滤了，不扫描加了A 注解的类
@EnableCircuitBreaker
public class ConsumerStartApp {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerStartApp.class,args);
    }




}
