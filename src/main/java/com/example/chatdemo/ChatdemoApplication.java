package com.example.chatdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ChatdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatdemoApplication.class, args);
    }
}
