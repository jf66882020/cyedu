package com.cy6688.eduservice.controller;


import com.cy6688.commonutils.R;
import com.cy6688.eduservice.entity.EduSubject;
import com.cy6688.eduservice.entity.SubjectTreeNode;
import com.cy6688.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 俊峰
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *添加课程分类
    */
    @PostMapping("/add")
    public R addSubject(MultipartFile file){
        subjectService.addSubject(file);
        return R.ok();
    }

    @GetMapping("/tree")
    public R getSubjectTree(){
        List<SubjectTreeNode> subjectTree = subjectService.getSubjectTree();
        return R.ok().data("items",subjectTree);
    }


    @GetMapping("/findAllOne")
    public R getAllOneSubject(){
        List<EduSubject> allOneSubject = subjectService.getAllOneSubject();
        return R.ok().data("items",allOneSubject);
    }

    @GetMapping("/findSonSubjects/{parentId}")
    public R getSubjects(@PathVariable("parentId") long parentId){
        List<EduSubject> subjectsByParentId = subjectService.getSubjectsByParentId(parentId);
        return R.ok().data("items",subjectsByParentId);
        //http://192.168.1.203:8888/eduservice/subject/findSonSubjects/1178214681139539969
    }
}

