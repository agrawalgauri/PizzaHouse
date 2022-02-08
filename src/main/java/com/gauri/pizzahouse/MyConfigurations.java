package com.gauri.pizzahouse;

import com.gauri.pizzahouse.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurations {

    @Bean
    public OrderService getOrderService(){
        return new OrderService(null);
    }

    
}


