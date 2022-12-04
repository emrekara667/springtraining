package com.etech.springtraining.app.webclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration{

    @Bean
    public WebClient webclient(){
       return WebClient.builder().baseUrl("http://localhost:8081/employeeservice").build();
    }
}
