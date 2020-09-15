package com.cy6688.servicevod;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @Author: 俊峰
 * @Date: 2020/9/14 9:44
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.cy6688"})
@Import(FdfsClientConfig.class)
@EnableDiscoveryClient
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}
