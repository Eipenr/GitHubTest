package com.eipen.springcloud.order.config;

import com.eipen.springcloud.anno.A;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@A
public class MyConfiguration {

    @Bean
    public IRule iRule(){
        return new RandomRule();
    }


}
