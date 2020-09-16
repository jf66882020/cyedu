package com.cy6688.statisticsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 俊峰
 * @Date: 2020/9/16 22:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.cy6688"})
@MapperScan(basePackages = {"com.cy6688.statisticsservice.mapper"})
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplication.class,args);
    }
}
