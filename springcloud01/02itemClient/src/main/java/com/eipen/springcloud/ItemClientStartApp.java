package com.eipen.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItemClientStartApp {

    public static void main(String[] args) {
        SpringApplication.run(ItemClientStartApp.class,args);
    }

}
