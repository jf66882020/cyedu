package com.cy6688.frontservice.controller;

import com.cy6688.commonutils.R;
import com.cy6688.frontservice.client.BannerClient;
import com.cy6688.frontservice.client.EduServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 10:18
 */
@RestController
@RequestMapping("/servicefront/index/")
@CrossOrigin
@Slf4j
public class IndexController {

    @Autowired
    private BannerClient bannerClient;

    @Autowired
    private EduServiceClient eduServiceClient;


    /**
    *@Author: 俊峰
    *@param
    *@return
    *查询前4条广告条
    */
    @GetMapping("getBanners")
    public R getBanners(){
        R r = bannerClient.findAllBanner();
        return r;
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *查询热门课程
    */
    @GetMapping("getHotCourse")
    public R getHotCourse(){
        return eduServiceClient.hotCourse();
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *查询热门讲师
    */
    @GetMapping("getHotTeacher")
    public R getHotTeacher(){
        return eduServiceClient.hotTeacher();
    }
}
