package com.cy6688.eduservice.client;

import com.cy6688.commonutils.R;
import com.cy6688.eduservice.client.impl.UcenterClientImpl;
import com.cy6688.eduservice.entity.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 俊峰
 * @Date: 2020/9/16 12:01
 */
@Component
@FeignClient(name = "SERVICE-UCENTER",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @GetMapping("/ucenterservice/member/front/info/{memberId}")
    public UcenterMember getUser(@PathVariable("memberId") String memberId);
}
