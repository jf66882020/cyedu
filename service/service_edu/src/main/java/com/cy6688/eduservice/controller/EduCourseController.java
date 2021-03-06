package com.cy6688.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy6688.commonutils.R;
import com.cy6688.eduservice.entity.CourseInfoVO;
import com.cy6688.eduservice.entity.EduCourse;
import com.cy6688.eduservice.entity.course.CourseQuery;
import com.cy6688.eduservice.entity.course.CourseWebVo;
import com.cy6688.eduservice.entity.course.FrontCouseQuery;
import com.cy6688.eduservice.entity.course.PublishCourseInfo;
import com.cy6688.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据课程id，获取课程发布信息
    */
    @GetMapping("get/publishInfo/{courseId}")
    public R getPublishInfo(@PathVariable("courseId") String courseId){
        PublishCourseInfo coursePublishInfo = courseService.getCoursePublishInfo(courseId);
        return R.ok().data("item",coursePublishInfo);
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *发布课程信息--修改课程状态
    */
    @PostMapping("publish/{courseId}")
    public R publishCourse(@PathVariable("courseId") String courseId){
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");
        boolean updateStatus = courseService.updateById(course);
        if(updateStatus){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *分页获取课程列表
    */
    @PostMapping("list/page/{current}/{limit}")
    public R getListWithPage(@RequestBody CourseQuery courseQuery, @PathVariable("current") long current, @PathVariable("limit") long limit){
        List<PublishCourseInfo> list = courseService.getCourseListWithPage(courseQuery,current,limit);
        long total = courseService.getCourseListWithPageSize(courseQuery);
        return R.ok().data("items",list).data("total",total);
    }

    @DeleteMapping("remove/{courseId}")
    public R removeCourse(@PathVariable("courseId") String courseId){
        boolean flag = courseService.removeCourseById(courseId);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *获取热门课程
     * 用于前端展示
    */
    @GetMapping("hot")
    public R hotCourse(){
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.last("limit 8");
        List<EduCourse> list = courseService.list(wrapper);
        return R.ok().data("items",list);
    }


    /**
    *@Author: 俊峰
    *@param
    *@return
    *前端课程列表页，带分页条件查询
    */
    @PostMapping("/list/front/complex/{current}/{limit}")
    public R getFrontCourseListWithPage(@RequestBody FrontCouseQuery courseQuery, @PathVariable("current") long current, @PathVariable("limit") long limit){
        Page<EduCourse> page = new Page<>(current,limit);
        Map<String,Object> map = courseService.frontComplexQuery(page,courseQuery);
        return R.ok().data(map);
    }

    @GetMapping("/detail/front/{courseId}")
    public R getFrontCourseDetail(@PathVariable("courseId") String courseId){
        Map<String, Object> map = courseService.getCourseDetailChapterVideo(courseId);
        return R.ok().data(map);
    }

}

