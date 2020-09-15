package com.cy6688.cmsservice;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 11:09
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.cy6688.cmsservice.mapper"})
@ComponentScan(basePackages = {"com.cy6688"})
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
