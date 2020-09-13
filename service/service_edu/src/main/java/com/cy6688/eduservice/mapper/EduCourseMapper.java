package com.cy6688.eduservice.mapper;

import com.cy6688.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cy6688.eduservice.entity.course.CourseQuery;
import com.cy6688.eduservice.entity.course.PublishCourseInfo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    PublishCourseInfo getPublishCourseInfo(String courseId);

    List<PublishCourseInfo> getListWithCoditionPage(HashMap map);

    long getListWithCoditionPageSize(HashMap map);
}
