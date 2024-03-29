package com.upspoon.analytic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AnalyticApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticApplication.class, args);
    }

}
