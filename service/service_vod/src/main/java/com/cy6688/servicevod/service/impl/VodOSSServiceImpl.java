package com.cy6688.servicevod.service.impl;

import com.cy6688.servicevod.constant.OSSConstant;
import com.cy6688.servicevod.service.VodOSSService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: 俊峰
 * @Date: 2020/9/14 9:50
 */
@Service
public class VodOSSServiceImpl implements VodOSSService {
    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Override
    public String uploadVideoToFDFS(MultipartFile file) {
        String result = "";
        try {
            InputStream inputStream = file.getInputStream();
            long size = file.getSize();
            String fileName = file.getOriginalFilename();
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, size, extName, null);
            System.out.println(storePath.getPath());
            String uploadedFileName = storePath.getPath().substring(storePath.getPath().lastIndexOf("/") + 1);
//            String noExtName = uploadedFileName.substring(0,uploadedFileName.lastIndexOf("."));
            result = uploadedFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void removeVideoFromFDFS(String fileName){
        String filePath = OSSConstant.NGINX_FDFS_GROUP + OSSConstant.NGINX_FDFS_PREFIX + fileName;
        fastFileStorageClient.deleteFile(filePath);
    }
}
