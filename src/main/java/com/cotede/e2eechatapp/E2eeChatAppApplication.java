package com.cotede.e2eechatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class E2eeChatAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(E2eeChatAppApplication.class, args);
    }

}
