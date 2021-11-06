package com.eipen.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulStartApp {

    public static void main(String[] args) {
        SpringApplication.run(ZuulStartApp.class,args);
    }

}
