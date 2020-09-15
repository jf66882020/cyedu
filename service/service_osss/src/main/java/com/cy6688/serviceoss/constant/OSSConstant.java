package com.cy6688.serviceoss.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 俊峰
 * @Date: 2020/9/11 13:23
 */
@Component
public class OSSConstant implements InitializingBean {

    @Value("${nginx.fdfs.host}")
    private String nginxFdfsHost;

    @Value("${nginx.fdfs.port}")
    private String nginxFdfsPort;

    @Value(("${subject.templateFile}"))
    private String subjectTemplate;

    public static String NGINX_FDFS_HOST = "";
    public static String NGINX_FDFS_PORT = "";
    public static String SUBJECT_TEMPLATE = "";


    @Override
    public void afterPropertiesSet() throws Exception {
        NGINX_FDFS_HOST = this.nginxFdfsHost;
        NGINX_FDFS_PORT = this.nginxFdfsPort;
        SUBJECT_TEMPLATE = this.subjectTemplate;
    }
}
