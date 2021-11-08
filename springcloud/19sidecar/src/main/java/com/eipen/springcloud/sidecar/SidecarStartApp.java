package com.eipen.springcloud.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class SidecarStartApp {

    public static void main(String[] args) {
        SpringApplication.run(SidecarStartApp.class,args);
    }



}
