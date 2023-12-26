package com.jvkstackmvn.jvkstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JvkstackApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvkstackApplication.class, args);
    }

}
