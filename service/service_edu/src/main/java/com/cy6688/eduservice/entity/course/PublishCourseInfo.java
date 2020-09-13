package com.cy6688.eduservice.entity.course;

import lombok.Data;

/**
 * @Author: 俊峰
 * @Date: 2020/9/13 11:25
 */
@Data
public class PublishCourseInfo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
    private String description;
    private String gmtCreate;
    private String status;
}
