package com.eipen.springcloud;

import com.eipen.springcloud.anno.A;
import com.eipen.springcloud.config.MyOpenfeginConfig;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@RibbonClient(value = "02ITEMCLIENT",configuration = MyOpenfeginConfig.class)
@ComponentScan(includeFilters = {@ComponentScan.Filter(A.class)})
@EnableCircuitBreaker //开启服务降级
public class OrderClientStartApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderClientStartApp.class,args);
    }

}
