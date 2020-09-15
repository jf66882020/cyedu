package com.cy6688.frontservice.client;

import com.cy6688.commonutils.R;
import com.cy6688.frontservice.client.impl.BannerClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 10:20
 */
@FeignClient(name = "SERVICE-CMS",fallback = BannerClientImpl.class)
@Component
public interface BannerClient {

    @GetMapping("/cmsservice/banner/front/findHot")
    public R findAllBanner();
}
