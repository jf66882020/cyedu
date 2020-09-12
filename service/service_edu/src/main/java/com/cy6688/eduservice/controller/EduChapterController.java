package com.cy6688.eduservice.controller;


import com.cy6688.commonutils.R;
import com.cy6688.eduservice.entity.EduChapter;
import com.cy6688.eduservice.entity.chapter.ChapterNode;
import com.cy6688.eduservice.service.EduChapterService;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据课程Id获取章节和小节
    */
    @GetMapping("/get/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterNode> nodeList = chapterService.getChapterVideoListByCourseId(courseId);
        return R.ok().data("items",nodeList);
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *添加章节
    */
    @PostMapping("/add")
    public R addChapter(@RequestBody EduChapter chapter){
        boolean saveStatus = chapterService.save(chapter);
        if(saveStatus){
            return R.ok();
        }else{
            return R.error().message("添加章节失败！");
        }
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *根据章节id，获取章节信息
    */
    @GetMapping("/one/{chapterId}")
    public R getChapter(@PathVariable("chapterId") String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        return R.ok().data("item",chapter);
    }

    /**
    *@Author: 俊峰
    *@param
    *@return
    *编辑章节信息
    */
    @PostMapping("/edit")
    public R editChapter(@RequestBody EduChapter chapter){
        boolean updateStatus = chapterService.updateById(chapter);
        if(updateStatus){
            return R.ok();
        }else{
            return R.error().message("编辑章节失败!");
        }
    }

    @DeleteMapping("/remove/{chapterId}")
    public R removeChapter(@PathVariable String chapterId){
        boolean removeStatus = chapterService.removeChapterById(chapterId);
        if(removeStatus){
            return R.ok();
        }else{
            return R.error();
        }
    }
}

