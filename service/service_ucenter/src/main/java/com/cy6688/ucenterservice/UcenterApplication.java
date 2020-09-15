package com.cy6688.ucenterservice;

import com.cy6688.ucenterservice.entity.UcenterMember;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 15:41
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.cy6688.ucenterservice.mapper"})
@ComponentScan(basePackages = {"com.cy6688"})
@EnableDiscoveryClient
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
