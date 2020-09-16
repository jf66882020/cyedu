package com.cy6688.eduservice.client.impl;

import com.cy6688.commonutils.R;
import com.cy6688.eduservice.client.UcenterClient;
import com.cy6688.eduservice.entity.UcenterMember;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 俊峰
 * @Date: 2020/9/16 12:08
 */
@Component
public class UcenterClientImpl implements UcenterClient {

    @Override
    public UcenterMember getUser(String memberId) {
        return null;
    }
}
