package com.cy6688.serviceoss.controller;

import com.cy6688.commonutils.R;
import com.cy6688.serviceoss.service.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author: 俊峰
 * @Date: 2020/9/11 12:45
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/ossservice/oss/")
public class OSSController {

    @Autowired
    private OSSService ossService;

    @PostMapping("uploadFile")
    private R uploadFile(MultipartFile multipartFile) throws IOException {
        String fileFullpath = "";
        if(multipartFile != null){
            fileFullpath = ossService.uploadOSSFile(multipartFile);
        }

        if(StringUtils.isNotEmpty(fileFullpath)){
            return R.ok().data("item",fileFullpath);
        }else{
            return R.error();
        }
    }

    @GetMapping("download/subjectTemplate")
    public void downloadSubjectTemplate(HttpServletResponse response){
        byte[] bytes = ossService.downloadSubjectTemplateOSSFile();
        ServletOutputStream outputStream = null;
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("课程分类模板.xlsx", "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
