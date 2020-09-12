package com.cy6688.serviceoss.service.impl;

import com.cy6688.serviceoss.OSSConstant;
import com.cy6688.serviceoss.service.OSSService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

/**
 * @Author: 俊峰
 * @Date: 2020/9/11 12:58
 */
@Service
public class OSSServiceImpl implements OSSService {

    @Resource
    private FastFileStorageClient fastFileStorageClient;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *上传文件到fastdfs上
    */
    public String uploadOSSFile(MultipartFile file) throws IOException {
        String result = "";
        String fileName = file.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        long fileLength = file.getSize();
        InputStream inputStream = file.getInputStream();
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, fileLength, extName, null);
        inputStream.close();

        String host = OSSConstant.NGINX_FDFS_HOST;
        String port = OSSConstant.NGINX_FDFS_PORT;
        result = "http://" + host + ":" + port + "/" + storePath.getFullPath();
        return result;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *从fastdfs上下载课程分类模板文件
    */
    @Override
    public byte[] downloadSubjectTemplateOSSFile() {
        String host = OSSConstant.NGINX_FDFS_HOST;
        String port = OSSConstant.NGINX_FDFS_PORT;
        String subjectTemplate = OSSConstant.SUBJECT_TEMPLATE;
        String group = subjectTemplate.substring(0,subjectTemplate.indexOf("/"));
        String path = subjectTemplate.substring(subjectTemplate.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }
}
