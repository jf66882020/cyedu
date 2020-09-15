package com.cy6688.servicevod.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 俊峰
 * @Date: 2020/9/11 13:23
 * TODO 注入貌似有问题，应该是从配置文件中获取
 */
@Component
public class OSSConstant implements InitializingBean {

    @Value("${nginx.fdfs.host}")
    private String nginxFdfsHost;

    @Value("${nginx.fdfs.port}")
    private String nginxFdfsPort;

    @Value("group1")
    private String nginxFdfsGroup;

    @Value("/M00/00/00/")
    private String nginxFdfsPrefix;

    public static String NGINX_FDFS_HOST = "";
    public static String NGINX_FDFS_PORT = "";
    public static String NGINX_FDFS_GROUP = "";
    public static String NGINX_FDFS_PREFIX = "";

    @Override
    public void afterPropertiesSet() throws Exception {
        NGINX_FDFS_HOST = this.nginxFdfsHost;
        NGINX_FDFS_PORT = this.nginxFdfsPort;
        NGINX_FDFS_GROUP = this.nginxFdfsGroup;
        NGINX_FDFS_PREFIX = this.nginxFdfsPrefix;
    }
}
