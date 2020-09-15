package com.cy6688.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy6688.cmsservice.entity.BannerQuery;
import com.cy6688.cmsservice.entity.CrmBanner;
import com.cy6688.cmsservice.mapper.CrmBannerMapper;
import com.cy6688.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-15
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
    *@Author: 俊峰
    *@param
    *@return
    *分页条件查询广告条列表
    */
    @Override
    public Page<CrmBanner> queryListWithPage(BannerQuery bannerQuery, long current, long limit) {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(bannerQuery.getTitle())){
            wrapper.like("title",bannerQuery.getTitle());
        }
        if(StringUtils.isNotBlank(bannerQuery.getStartTime())){
            wrapper.ge("gmt_create",bannerQuery.getStartTime());
        }
        if(StringUtils.isNotBlank(bannerQuery.getEndTime())){
            wrapper.le("gmt_create",bannerQuery.getEndTime());
        }
        Page<CrmBanner> page = new Page<>();
        page.setCurrent(current);
        page.setSize(limit);
        this.page(page,wrapper);
        return page;
    }

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> hotList() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
