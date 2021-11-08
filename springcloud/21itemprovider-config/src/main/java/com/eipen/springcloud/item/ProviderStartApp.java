package com.eipen.springcloud.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //开启服务发现
@EnableCircuitBreaker
public class ProviderStartApp {


    public static void main(String[] args) {
        SpringApplication.run(ProviderStartApp.class,args);
    }

}
