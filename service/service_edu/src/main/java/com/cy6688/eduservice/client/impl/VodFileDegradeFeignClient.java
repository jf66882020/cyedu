package com.cy6688.eduservice.client.impl;

import com.cy6688.commonutils.R;
import com.cy6688.eduservice.client.VodClient;
import com.cy6688.servicebase.exception.CyException;
import org.springframework.stereotype.Component;

/**
 * @Author: 俊峰
 * @Date: 2020/9/14 21:19
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {

    /**
    *@Author: 俊峰
    *@param
    *@return
    *删除视频断路器
    */
    @Override
    public R removeVideo(String filePath) {
        throw new CyException(20001,"删除视频出错了");
    }
}
