package com.cy6688.cmsservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cy6688.cmsservice.entity.CrmBanner;
import com.cy6688.cmsservice.service.CrmBannerService;
import com.cy6688.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.List;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 9:37
 */
@RestController
@CrossOrigin
@RequestMapping("/cmsservice/banner/front")
public class FrontBannerController {

    @Autowired
    private CrmBannerService bannerService;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *查询所有广告条，用于前台显示
    */
    @GetMapping("/findHot")
    public R findAllBanner(){
        List<CrmBanner> list = bannerService.hotList();

        if(list != null && list.size() != 0){
            return R.ok().data("items",list).message("获取广告条列表成功!");
        }else{
            return R.error().message("获取广告条列表失败!");
        }
    }

}
