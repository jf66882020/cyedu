package com.cy6688.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cy6688.commonutils.R;
import com.cy6688.eduservice.entity.EduCourse;
import com.cy6688.eduservice.entity.EduTeacher;
import com.cy6688.eduservice.entity.TeacherQuery;
import com.cy6688.eduservice.service.EduCourseService;
import com.cy6688.eduservice.service.EduTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-08
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
@Slf4j
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService courseService;

    //查询讲师表中所有数据
    @GetMapping("/findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    //逻辑删除讲师
    @DeleteMapping("/delete/{id}")
    public R deleteTeacher(@PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    /** 讲师分页查询
    *@Author: 俊峰
    *@param
    *@return
    */
    @GetMapping("/list/page/{current}/{limit}")
    public R pageList(@PathVariable("current") long current,
                      @PathVariable("limit") long limit){

        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page,null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("items",records).data("total",total);
    }

    /**根据条件分页查询讲师列表
    *@Author: 俊峰
    *@param
    *@return
    */
    @PostMapping("/list/complexQuery/{current}/{limit}")
    public R complexSearch(@PathVariable("current") long current,
                           @PathVariable("limit")   long limit,
                           @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page = new Page<>(current,limit);
        Integer level = teacherQuery.getLevel();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("gmt_create");
        if(StringUtils.isNotEmpty(teacherQuery.getName())){
            wrapper.like("name",teacherQuery.getName().trim());
        }
        if(level != null){
            wrapper.eq("level",teacherQuery.getLevel());
        }
        if(StringUtils.isNotEmpty(teacherQuery.getBegin())){
            wrapper.ge("gmt_create",teacherQuery.getBegin());
        }
        if(StringUtils.isNotEmpty(teacherQuery.getEnd())){
            wrapper.le("gmt_create",teacherQuery.getEnd());
        }
        eduTeacherService.page(page,wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total",total).data("items",records);
    }

    /**添加讲师
    *@Author: 俊峰
    *@param
    *@return
    */
    @PostMapping("/add")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据id查询讲师
    */
    @GetMapping("/get/{teacherId}")
    public R getTeacherById(@PathVariable("teacherId") String teacherId){
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        return R.ok().data("item",teacher);
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *讲师修改
    */
    @PostMapping("/edit")
    public R updateTeacherById(@RequestBody EduTeacher teacher){
        boolean update = eduTeacherService.updateById(teacher);
        if(update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/remove/{teacherId}")
    public R removeTeacherById(@PathVariable("teacherId") String teacherId){
        boolean flag = eduTeacherService.removeById(teacherId);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @GetMapping("/hot")
    public R hotTeacher(){
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.last("limit 4");
        List<EduTeacher> list = eduTeacherService.list(wrapper);
        return R.ok().data("items",list);
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *分页获取前端名师列表
    */
    @GetMapping("/list/front/{page}/{limit}")
    public R frontTeacherList(@PathVariable long page,@PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page(page,limit);
        Map<String, Object> frontTeachers = eduTeacherService.getFrontTeachers(pageTeacher);
        return R.ok().data(frontTeachers);
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据讲师Id,获取讲师详情和该讲师所讲课程
    */
    @GetMapping("/get/front/{teacherId}")
    public R getTeacherWithCourse(@PathVariable("teacherId") String teacherId){
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.eq("teacher_id",teacherId);
        wrapper.last("limit 4");
        List<EduCourse> courses = courseService.list(wrapper);

        return R.ok().data("item",teacher).data("items",courses);
    }

}

