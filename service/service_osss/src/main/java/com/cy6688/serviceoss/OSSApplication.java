package com.cy6688.serviceoss;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @Author: 俊峰
 * @Date: 2020/9/11 12:44
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.cy6688"})
@EnableDiscoveryClient
@Import(FdfsClientConfig.class)
public class OSSApplication {
    public static void main(String[] args) {
        SpringApplication.run(OSSApplication.class,args);
    }
}
