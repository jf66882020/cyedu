package com.cy6688.frontservice.client.impl;

import com.cy6688.commonutils.R;
import com.cy6688.frontservice.client.BannerClient;
import org.springframework.stereotype.Component;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 10:28
 */
@Component
public class BannerClientImpl implements BannerClient {
    @Override
    public R findAllBanner() {
        return R.error().message("查询热闹广告条失败!");
    }
}
