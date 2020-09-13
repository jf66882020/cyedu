package com.cy6688.eduservice.service;

import com.cy6688.eduservice.entity.CourseInfoVO;
import com.cy6688.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cy6688.eduservice.entity.course.CourseQuery;
import com.cy6688.eduservice.entity.course.PublishCourseInfo;

import java.util.List;

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

    //发布课程信息
    PublishCourseInfo getCoursePublishInfo(String courseId);

    //分页获取课程信息
    List<PublishCourseInfo> getCourseListWithPage(CourseQuery courseQuery, long current, long limit);

    //条件查询的总记录数
    public long getCourseListWithPageSize(CourseQuery courseQuery);

    boolean removeCourseById(String courseId);
}
