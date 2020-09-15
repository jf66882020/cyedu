package com.cy6688.servicevod.controller;

import com.cy6688.commonutils.R;
import com.cy6688.servicevod.service.VodOSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 俊峰
 * @Date: 2020/9/14 9:45
 */
@RestController
@RequestMapping("/vodservice/video/")
@Slf4j
@CrossOrigin
public class VodOSSController {

    @Autowired
    private VodOSSService ossService;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *上传视频到fastDFS上
    */
    @PostMapping("upload")
    public R uploadVideo(MultipartFile file){
        String fileUrl = ossService.uploadVideoToFDFS(file);
        return R.ok().data("item",fileUrl);
    }

    @GetMapping("remove/{filename}")
    public R removeVideo(@PathVariable("filename") String filePath){
        ossService.removeVideoFromFDFS(filePath);
        return R.ok().message("删除视频成功！");
    }
}
