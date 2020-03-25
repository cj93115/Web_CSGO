package com.Web_CSGO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class runApplication {

    @Autowired
    private RestTemplateBuilder templateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(runApplication.class, args);
        System.out.println("success!");
    }
    @Bean
    public RestTemplate getRestTemplate(){
        return templateBuilder.build();
    }

}
