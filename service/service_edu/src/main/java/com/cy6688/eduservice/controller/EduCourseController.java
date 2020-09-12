package com.cy6688.eduservice.controller;


import com.cy6688.commonutils.R;
import com.cy6688.eduservice.entity.CourseInfoVO;
import com.cy6688.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-12
 */
@RestController
@RequestMapping("/eduservice/course/")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *添加课程基本信息
    */
    @PostMapping("add")
    public R add(@RequestBody CourseInfoVO courseInfoVO){
        String courseId = courseService.saveCourseInfo(courseInfoVO);
        return R.ok().data("item",courseId);
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据课程id获取课程基本信息
    */
    @GetMapping("one/{courseId}")
    public R getCourseInfo(@PathVariable("courseId") String courseId){
        CourseInfoVO courseInfo = courseService.getCourseInfo(courseId);
        return R.ok().data("item",courseInfo);
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *修改课程基本信息
    */
    @PostMapping("edit")
    public R editCourseInfo(@RequestBody CourseInfoVO courseInfoVO){
        courseService.editCourseInfo(courseInfoVO);
        return R.ok();
    }

}

