package com.cy6688.frontservice.client;

import com.cy6688.commonutils.R;
import com.cy6688.frontservice.client.impl.CourseClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 10:20
 */
@FeignClient(name = "SERVICE-EDU",fallback = CourseClientImpl.class)
@Component
public interface EduServiceClient {

    @GetMapping("/eduservice/course/hot")
    public R hotCourse();

    @GetMapping("/eduservice/teacher/hot")
    public R hotTeacher();

}
