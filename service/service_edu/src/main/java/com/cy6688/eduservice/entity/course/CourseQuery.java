package com.cy6688.eduservice.entity.course;

import lombok.Data;

/**
 * @Author: 俊峰
 * @Date: 2020/9/13 20:08
 */
@Data
public class CourseQuery {
    //讲师id
    private String teacherName;
    //课程一级分类
    private String subjectParentId;
    //课程二级分类
    private String subjectId;
    //课程名称
    private String title;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //课程状态
    private String status;
}
