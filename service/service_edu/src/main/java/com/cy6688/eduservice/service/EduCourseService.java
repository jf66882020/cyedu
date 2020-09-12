package com.cy6688.eduservice.service;

import com.cy6688.eduservice.entity.CourseInfoVO;
import com.cy6688.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息
    String saveCourseInfo(CourseInfoVO courseInfoVO);

    //根据课程id获取课程基本信息
    CourseInfoVO getCourseInfo(String courseId);

    //修改课程基本信息
    void editCourseInfo(CourseInfoVO courseInfoVO);

}
