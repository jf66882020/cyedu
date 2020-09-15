package com.cy6688.eduservice.client;

import com.cy6688.commonutils.R;
import com.cy6688.eduservice.client.impl.VodFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: 俊峰
 * @Date: 2020/9/14 13:30
 */
@FeignClient(name = "SERVICE-VOD",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @GetMapping("/vodservice/video/remove/{filename}")
    public R removeVideo(@PathVariable("filename") String filePath);
}
