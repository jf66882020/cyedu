package com.cy6688.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.cy6688.eduservice.entity.EduTeacher;
import com.cy6688.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师表中所有数据
    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    //逻辑删除讲师
    @DeleteMapping("/delete/{id}")
    public boolean deleteTeacher(@PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        return flag;
    }

}

