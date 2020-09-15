package com.cy6688.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy6688.cmsservice.entity.BannerQuery;
import com.cy6688.cmsservice.entity.CrmBanner;
import com.cy6688.cmsservice.service.CrmBannerService;
import com.cy6688.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-15
 */
@RestController
@RequestMapping("/cmsservice/banner/admin")
@CrossOrigin
public class AdminBannerController {

    @Autowired
    private CrmBannerService bannerService;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *添加广告条
    */
    @PostMapping("/add")
    public R addBanner(@RequestBody CrmBanner banner){
        boolean saveStatus = bannerService.save(banner);
        if(saveStatus){
            return R.ok().message("添加广告条成功!");
        }else{
            return R.error().message("添加广告条失败!");
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *删除广告条
    */
    @DeleteMapping("/remove/{bannerId}")
    public R removeBanner(@PathVariable("bannerId") String bannerId){
        boolean removeStatus = bannerService.removeById(bannerId);
        if(removeStatus){
            return R.ok().message("删除广告条成功!");
        }else{
            return R.error().message("删除广告条失败!");
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *获取广告条（根据id)
    */
    @GetMapping("/get/{bannerId}")
    public R getBanner(@PathVariable("bannerId") String bannerId){
        CrmBanner banner = bannerService.getById(bannerId);
        if(banner != null){
            return R.ok().data("item",banner).message("获取banner成功!");
        }else{
            return R.error().message("获取banner失败!");
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *编辑广告条
    */
    @PostMapping("/edit")
    public R editBanner(@RequestBody CrmBanner banner){
        boolean updateStatus = bannerService.updateById(banner);
        if(updateStatus){
            return R.ok().message("编辑banner成功!");
        }else{
            return R.error().message("编辑banner失败!");
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *分页条件查询广告条
    */
    public R queryWithPage(@RequestBody BannerQuery bannerQuery, @PathVariable("current") long current, @PathVariable("limit") long limit){
        Page<CrmBanner> page = new Page<>();
        Page<CrmBanner> bannerPage = bannerService.queryListWithPage(bannerQuery, current, limit);
        long total = bannerPage.getTotal();
        List<CrmBanner> records = bannerPage.getRecords();
        return R.ok().data("total",total).data("items",records).message("返回列表成功!");
    }
}

