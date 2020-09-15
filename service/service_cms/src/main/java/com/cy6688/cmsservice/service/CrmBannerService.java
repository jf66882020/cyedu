package com.cy6688.cmsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy6688.cmsservice.entity.BannerQuery;
import com.cy6688.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-15
 */
public interface CrmBannerService extends IService<CrmBanner> {

    Page<CrmBanner> queryListWithPage(BannerQuery bannerQuery, long current, long limit);

    List<CrmBanner> hotList();
}
