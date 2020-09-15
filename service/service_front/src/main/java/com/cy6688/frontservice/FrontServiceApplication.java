package com.cy6688.frontservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 10:15
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.cy6688"})
@EnableDiscoveryClient
public class FrontServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontServiceApplication.class,args);
    }
}
