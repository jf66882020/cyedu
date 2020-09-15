package com.cy6688.servicevod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 俊峰
 * @Date: 2020/9/14 9:49
 */
public interface VodOSSService {
    public String uploadVideoToFDFS(MultipartFile file);

    public void removeVideoFromFDFS(String filePath);
}
