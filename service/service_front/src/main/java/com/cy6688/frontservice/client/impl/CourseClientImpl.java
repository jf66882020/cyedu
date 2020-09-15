package com.cy6688.frontservice.client.impl;

import com.cy6688.commonutils.R;
import com.cy6688.frontservice.client.EduServiceClient;
import org.springframework.stereotype.Component;

/**
 * @Author: 俊峰
 * @Date: 2020/9/15 10:29
 */
@Component
public class CourseClientImpl implements EduServiceClient {
    @Override
    public R hotCourse() {
        return R.error().message("查询热门课程失败!");
    }

    @Override
    public R hotTeacher() {
        return R.error().message("查询热门名师失败!");
    }
}
