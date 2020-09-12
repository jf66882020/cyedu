package com.cy6688.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: 俊峰
 * @Date: 2020/9/11 12:57
 */
public interface OSSService {
    public String uploadOSSFile(MultipartFile file) throws IOException;

    byte[] downloadSubjectTemplateOSSFile();
}
